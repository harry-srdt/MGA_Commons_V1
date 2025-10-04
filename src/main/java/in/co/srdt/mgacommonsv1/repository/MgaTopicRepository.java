package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.dto.subjectOfferingManagement.TopicsListItem;
import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface MgaTopicRepository extends JpaRepository<MgaTopic, Long> {
    // All topics under a subject
    @Query(value = """
        select t.mga_topic_id as topicId,
               t.title        as topicTitle
        from mga_subject s
        join mga_unit u  on u.mga_subject_id = s.mga_subject_id
        join mga_topic t on t.mga_unit_id = u.mga_unit_id
        where s.code = :code
          and coalesce(u.active_status,'A')='A'
          and coalesce(t.active_status,'A')='A'
        order by u.display_sequence, t.display_sequence, t.mga_topic_id
        """, nativeQuery = true)
    List<Object[]> findAllTopicsForSubject(@Param("code") String subjectCode);

    @Query(value = """
        select a.mga_topic_id as topicId,
               count(*)        as evaluatedAttempts,
               (
                 select a2.obtained_marks
                 from mga_assessment_attempt a2
                 join core_assessment_attempt_status st2
                   on st2.core_assessment_attempt_status_id = a2.core_assessment_attempt_status_id
                 where a2.mga_student_id = :studentId
                   and a2.mga_topic_id   = a.mga_topic_id
                   and upper(st2.code)   = 'EVALUATED'
                 order by a2.evaluated_at desc, a2.mga_assessment_attempt_id desc
                 limit 1
               ) as lastObtainedMarks
        from mga_assessment_attempt a
        join core_assessment_attempt_status st
          on st.core_assessment_attempt_status_id = a.core_assessment_attempt_status_id
        where a.mga_student_id = :studentId
          and upper(st.code)   = 'EVALUATED'
        group by a.mga_topic_id
        """, nativeQuery = true)
    List<Object[]> findEvaluatedAggForStudent(@Param("studentId") Long studentId);

    List<MgaTopic> findByMgaUnit_MgaUnitId(Long unitId);

    @Query("""
        select t from MgaTopic t
        where t.mgaUnit.mgaUnitId in :unitIds and coalesce(t.activeStatus,'A')='A'
        order by t.mgaUnit.displaySequence, t.displaySequence, t.mgaTopicId
    """)
    List<MgaTopic> findActiveByUnitIds(@Param("unitIds") List<Long> unitIds);

    List<MgaTopic> findByMgaUnit_MgaUnitIdInOrderByDisplaySequenceAsc(Collection<Long> unitIds);

    @Query(value = """
        select
            mga_topic_id,
            title
        from
            mga_topic
        where
            mga_unit_id = :unitId
            and coalesce(active_status, 'A') = 'A'
    """, nativeQuery = true)
    List<TopicsListItem> findByMgaUnitId(@Param("unitId") Long unitId);
}