package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAssessment.MgaAssessmentAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MgaAssessmentAttemptRepository extends JpaRepository<MgaAssessmentAttempt, Long> {
    List<MgaAssessmentAttempt> findByMgaStudent_MgaStudentIdAndMgaTopic_MgaTopicIdOrderByStartedAtDesc(Long studentId, Long topicId);

    @Query(value = """
        select
          a.mga_assessment_attempt_id as attemptId,
          a.evaluated_at              as evaluatedAt,
          a.total_marks               as totalMarks,
          a.obtained_marks            as obtainedMarks,
          (select count(*)
           from mga_assessment_attempt_question q
           where q.mga_assessment_attempt_id = a.mga_assessment_attempt_id) as questionCount,
          (select count(*)
           from mga_assessment_attempt_answer ans
           join mga_assessment_attempt_question q
             on q.mga_assessment_attempt_question_id = ans.mga_assessment_attempt_question_id
           where q.mga_assessment_attempt_id = a.mga_assessment_attempt_id
             and coalesce(ans.is_correct,0)=1) as correctCount
        from mga_assessment_attempt a
        join core_assessment_attempt_status st
          on st.core_assessment_attempt_status_id = a.core_assessment_attempt_status_id
        where a.mga_student_id = :studentId
          and a.mga_topic_id   = :topicId
          and upper(st.code)   = 'EVALUATED'
        order by a.evaluated_at desc, a.mga_assessment_attempt_id desc
        """, nativeQuery = true)
    List<Object[]> findEvaluatedAttemptSummaries(@Param("topicId") Long topicId,
                                                 @Param("studentId") Long studentId);

    @Query(value = """
    SELECT COUNT(*)
    FROM mga_assessment_attempt a
    JOIN core_assessment_attempt_status s
      ON s.core_assessment_attempt_status_id = a.core_assessment_attempt_status_id
    WHERE a.mga_student_id = :sid
      AND a.mga_subject_offering_id = :oid
      AND a.mga_topic_id = :tid
      AND a.mga_assessment_template_id = :tplId
      AND COALESCE(a.active_status, 'A') = 'A'
      AND s.code = 'EVALUATED'
""", nativeQuery = true)
    long countActiveAttempts(
            @Param("sid") Long studentId,
            @Param("oid") Long offeringId,
            @Param("tid") Long topicId,
            @Param("tplId") Long templateId
    );
}