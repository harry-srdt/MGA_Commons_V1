package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.dto.studentApis.raw.OfferingRow;
import in.co.srdt.mgacommonsv1.dto.studentApis.raw.RawSubjectRow;
import in.co.srdt.mgacommonsv1.dto.subjectOfferingManagement.ActiveOfferingRow;
import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaSubjectOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MgaSubjectOfferingRepository extends JpaRepository<MgaSubjectOffering, Long> {


    List<MgaSubjectOffering> findByMgaSubject_MgaSubjectId(Long mgaSubjectId);


    // Find available self-paced offerings for a student identified by roll number
    @Query(value = """
        SELECT DISTINCT
          so.mga_subject_offering_id                AS subjectOfferingId,
          s.code                                    AS subjectCode,
          s.title                                   AS subjectTitle,
          concat(mst.storage_path, mst.file_name)   AS subjectThumbnail,
          concat(concat(FLOOR(vstr.total_minutes / 60), ' Hours '), concat(MOD(vstr.total_minutes, 60), ' Minutes')) AS estimatedTime,
          vstr.topic_count                          AS lecturesCount,
          s.credits                                 AS subjectCredits,
          dm.code                                   AS deliveryMode,
          fac.name                                  AS facultyName,
          cls.code                                  AS classCode,
          term.name                                 AS termName,
          srs.avg_rating			                AS subjectRating,
          srs.ratings_count                         AS ratingCount,
          so.start_date                             AS startDate,
          so.end_date                               AS endDate
        FROM
        -- mga_program_subject ps
        mga_subject s -- ON ps.mga_subject_id = s.mga_subject_id
        LEFT JOIN mga_subject_offering so ON s.mga_subject_id = so.mga_subject_id
        LEFT JOIN core_delivery_mode dm ON so.core_delivery_mode_id = dm.core_delivery_mode_id
        LEFT JOIN mga_faculty fac ON so.mga_faculty_id = fac.mga_faculty_id
        LEFT JOIN mga_class_ps cls ON so.mga_class_ps_id = cls.mga_class_ps_id
        LEFT JOIN mga_term term ON so.mga_term_id = term.mga_term_id
        LEFT JOIN mga_subject_rating_summary srs ON s.mga_subject_id = srs.mga_subject_id
        LEFT JOIN mga_subject_thumbnail mst ON s.mga_subject_id = mst.mga_subject_id
        LEFT JOIN v_subject_time_rollup vstr ON s.mga_subject_id = vstr.mga_subject_id
        WHERE -- COALESCE(ps.active_status, 'A') = 'A' AND
          COALESCE(s.active_status, 'A') = 'A'
          AND COALESCE(mst.active_status, 'A') = 'A'
        --  AND EXISTS (
        --    SELECT 1
        --    FROM mga_student_program sp
        --    JOIN mga_student st
        --      ON sp.mga_student_id = st.mga_student_id
        --    WHERE sp.mga_program_id = ps.mga_program_id
        --      AND st.roll_number = :rollNumber
        --      AND COALESCE(sp.active_status, 'A') = 'A'
        --  )
          AND EXISTS (
            SELECT 1
            FROM mga_subject_offering soAvail
            JOIN core_delivery_mode dmAvail
              ON soAvail.core_delivery_mode_id = dmAvail.core_delivery_mode_id
            WHERE soAvail.mga_subject_id = s.mga_subject_id
              AND COALESCE(soAvail.active_status, 'A') = 'A'
              AND dmAvail.code = 'SELF_PACED'
          )
          AND NOT EXISTS (
            SELECT 1
            FROM mga_student_subject_enrollment enr
            JOIN mga_student st2
              ON enr.mga_student_id = st2.mga_student_id
            JOIN mga_subject_offering so2
              ON enr.mga_subject_offering_id = so2.mga_subject_offering_id
            JOIN core_delivery_mode dm2
              ON so2.core_delivery_mode_id = dm2.core_delivery_mode_id
            WHERE st2.roll_number = :rollNumber
              AND so2.mga_subject_id = s.mga_subject_id
              AND COALESCE(so2.active_status, 'A') = 'A'
              AND dm2.code = 'SELF_PACED'
              AND COALESCE(enr.active_status, 'A') = 'A'
              AND COALESCE(enr.status, 'A') = 'A'
          )
        ORDER BY s.code
        """, nativeQuery = true)
    List<RawSubjectRow> findAvailableSelfPacedOfferingsForRoll(@Param("rollNumber") String rollNumber);


    // Fetch the latest offering for a given subject code
    @Query(value = """
        select
          f.mga_faculty_id     as facultyId,
          f.name               as facultyName,
          f.email              as facultyEmail,
          f.specialization     as facultySpecialization,
          f.about              as facultyAbout,
          dm.code              as deliveryMode
        from mga_subject_offering so
        join mga_subject s on s.mga_subject_id = so.mga_subject_id
        join core_delivery_mode dm on dm.core_delivery_mode_id = so.core_delivery_mode_id
        left join mga_faculty f on f.mga_faculty_id = so.mga_faculty_id
        where s.code = :code
          and coalesce(so.active_status,'A') = 'A'
        order by coalesce(so.start_date, so.created_at) desc, so.mga_subject_offering_id desc
        limit 1
        """, nativeQuery = true)
    OfferingRow findLatestOffering(@Param("code") String subjectCode);


    // Count active subject offerings for a given faculty
    @Query(value = """
        select count(*) from mga_subject_offering so
        where so.mga_faculty_id = :fid
          and coalesce(so.active_status,'A') = 'A'
        """, nativeQuery = true)
    long countActiveOfferingsForFaculty(@Param("fid") long facultyId);


    // Fetch a subject offering along with its subject
    @Query("""
        select o from MgaSubjectOffering o
        join fetch o.mgaSubject s
        where o.mgaSubjectOfferingId = :offeringId and coalesce(o.activeStatus, 'A')='A'
    """)
    Optional<MgaSubjectOffering> fetchWithSubject(@Param("offeringId") Long offeringId);


    // Fetch an active subject offering along with its subject
    @Query("""
        select o from MgaSubjectOffering o
        join fetch o.mgaSubject s
        where o.mgaSubjectOfferingId = :offeringId
          and coalesce(o.activeStatus,'A')='A'
    """)
    Optional<MgaSubjectOffering> fetchActive(@Param("offeringId") Long offeringId);

    // Check if the given faculty owns the given subject offering
    @Query(value = """
        SELECT CASE WHEN EXISTS(
            SELECT 1
            FROM mga_subject_offering so
            WHERE so.mga_subject_offering_id = :offeringId
              AND so.mga_faculty_id = :facultyId
              AND COALESCE(so.active_status, 'A') = 'A'
        ) THEN 1 ELSE 0 END
        """, nativeQuery = true)
    int existsFacultyOwnership(@Param("offeringId") Long offeringId, @Param("facultyId") Long facultyId);


    @Query(value = """

            SELECT DISTINCT
                so.mga_subject_offering_id              AS subjectOfferingId,
                s.code                                  AS subjectCode,
                s.title                                 AS subjectTitle,
                CONCAT(mst.storage_path, mst.file_name) AS subjectThumbnail,
                CONCAT(FLOOR(vstr.total_minutes / 60), ' Hours ', MOD(vstr.total_minutes, 60), ' Minutes') AS estimatedTime,
                vstr.topic_count                        AS lecturesCount,
                s.credits                               AS subjectCredits,
                dm.code                                 AS deliveryMode,
                fac.name                                AS facultyName,
                cls.code                                AS classCode,
                term.name                               AS termName,
                ors.avg_rating                          AS averageRating,
                ors.ratings_count                       AS ratingCount,
                so.start_date                           AS startDate,
                so.end_date                             AS endDate,
            COALESCE(so.start_date, so.created_at)  AS sortKey
            FROM mga_subject_offering so
                JOIN mga_subject s              					ON s.mga_subject_id = so.mga_subject_id
                JOIN core_delivery_mode dm      					ON dm.core_delivery_mode_id = so.core_delivery_mode_id
                LEFT JOIN mga_faculty fac       					ON fac.mga_faculty_id = so.mga_faculty_id
                LEFT JOIN mga_class_ps cls      					ON cls.mga_class_ps_id = so.mga_class_ps_id
                LEFT JOIN mga_term term         					ON term.mga_term_id = so.mga_term_id
                LEFT JOIN mga_subject_thumbnail mst 				ON mst.mga_subject_id = s.mga_subject_id
                LEFT JOIN v_subject_time_rollup vstr 				ON vstr.mga_subject_id = s.mga_subject_id
                LEFT JOIN mga_subject_offering_rating_summary ors 	ON ors.mga_subject_offering_id = so.mga_subject_offering_id
            WHERE so.mga_subject_id = 29
                AND COALESCE(so.active_status, 'A') = 'A'
                AND COALESCE(s.active_status,  'A') = 'A'
                AND COALESCE(mst.active_status,'A') = 'A'
            ORDER BY sortKey DESC, subjectOfferingId DESC
        """, nativeQuery = true)
    List<ActiveOfferingRow> findActiveOfferingsBySubjectId(@Param("subjectId") Long subjectId);

    
}