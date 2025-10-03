package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.dto.assessmentApis.raw.TopRankerRow;
import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaSubjectOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MgaLeaderboardRepository extends JpaRepository<MgaSubjectOffering, Long> {

    @Query(value = """

            WITH
                offering AS (
                  SELECT o.mga_subject_offering_id AS oid, o.mga_subject_id AS sid
                  FROM mga_subject_offering o
                  WHERE o.mga_subject_offering_id = :offeringId
                    AND COALESCE(o.active_status,'A')='A'
                ),
                topics AS (
                  SELECT t.mga_topic_id AS tid
                  FROM mga_topic t
                  JOIN mga_unit u
                    ON u.mga_unit_id = t.mga_unit_id
                   AND COALESCE(u.active_status,'A')='A'
                  JOIN offering ofg
                    ON ofg.sid = u.mga_subject_id
                  WHERE COALESCE(t.active_status,'A')='A'
                ),
                enrolled AS (
                  SELECT DISTINCT enr.mga_student_id AS sid
                  FROM mga_student_subject_enrollment enr
                  WHERE enr.mga_subject_offering_id = :offeringId
                    AND COALESCE(enr.active_status,'A')='A'
                    AND COALESCE(enr.status,'A')='A'
                ),
                evaluated AS (
                  SELECT
                    aa.mga_student_id            AS sid,
                    aa.mga_topic_id              AS tid,
                    aa.mga_assessment_attempt_id AS aid,
                    aa.obtained_marks / NULLIF(aa.total_marks,0) AS pct,
                    COALESCE(aa.evaluated_at, aa.submitted_at, aa.created_at) AS ts
                  FROM mga_assessment_attempt aa
                  JOIN topics tp    ON tp.tid = aa.mga_topic_id
                  JOIN offering ofg ON ofg.oid = aa.mga_subject_offering_id
                  JOIN enrolled en  ON en.sid = aa.mga_student_id
                  JOIN core_assessment_attempt_status st
                    ON st.core_assessment_attempt_status_id = aa.core_assessment_attempt_status_id
                  WHERE COALESCE(aa.active_status,'A')='A'
                    AND st.code='EVALUATED'
                ),
                first_n AS (
                  SELECT e.*,
                         ROW_NUMBER() OVER (PARTITION BY sid, tid ORDER BY ts ASC, aid ASC) AS rn_time
                  FROM evaluated e
                ),
                within_n AS (
                  SELECT sid, tid, aid, pct, ts
                  FROM first_n
                  WHERE rn_time <= :maxAttempts
                ),
                best_per_topic AS (
                  SELECT sid, tid, pct, ts, aid,
                         ROW_NUMBER() OVER (PARTITION BY sid, tid ORDER BY pct DESC, ts DESC, aid DESC) AS rn_best
                  FROM within_n
                ),
                best1 AS (
                  SELECT sid, tid, pct
                  FROM best_per_topic
                  WHERE rn_best = 1
                ),
                agg AS (
                  SELECT sid,
                         AVG(pct) AS avg_pct,
                         COUNT(*) AS completed_topics
                  FROM best1
                  GROUP BY sid
                ),
                tot AS ( SELECT COUNT(*) AS total_topics FROM topics ),
                eligible AS (
                  SELECT a.sid, a.avg_pct, a.completed_topics, t.total_topics,
                         a.completed_topics / t.total_topics AS coverage
                  FROM agg a CROSS JOIN tot t
                  WHERE a.completed_topics >= CEIL(:minCoverage * t.total_topics)
                ),
                avatars AS (
                  SELECT
                    ua.username,
                    ua.file_name    AS avatar_file_name,
                    ua.storage_path AS avatar_storage_path,
                    ROW_NUMBER() OVER (
                      PARTITION BY ua.username
                      ORDER BY COALESCE(ua.is_primary,1) DESC, ua.created_at DESC, ua.mga_user_avatar_id DESC
                    ) AS rn
                  FROM mga_user_avatar ua
                  WHERE COALESCE(ua.active_status,'A')='A'
                ),
                ranked AS (
                  SELECT
                    DENSE_RANK() OVER (ORDER BY e.avg_pct DESC, e.coverage DESC) AS rnk,
                    s.mga_student_id,
                    CONCAT(COALESCE(s.first_name,''), ' ', COALESCE(s.last_name,'')) AS name,
                    ROUND(e.avg_pct*100, 2) AS percentage,
                    COALESCE(
                      CONCAT_WS('/',
                        NULLIF(TRIM(BOTH '/' FROM av.storage_path), ''),
                        av.file_name
                      ),
                      ''
                    ) AS avatar
                  FROM eligible e
                  JOIN mga_student s ON s.mga_student_id = e.sid
                  LEFT JOIN mga_user_avatar av ON av.username = s.roll_number AND av.active_status = 'A'
                )
                SELECT
                  rnk   AS `rank`,
                  name  AS `name`,
                  percentage AS `percentage`,
                  avatar AS `avatar`
                FROM ranked
                ORDER BY rnk, name
                LIMIT :topK
        """, nativeQuery = true)
    java.util.List<TopRankerRow> findTopRankersForOffering(
            @Param("offeringId") Long offeringId,
            @Param("topK") int topK,
            @Param("maxAttempts") int maxAttempts,
            @Param("minCoverage") double minCoverage
    );
}
