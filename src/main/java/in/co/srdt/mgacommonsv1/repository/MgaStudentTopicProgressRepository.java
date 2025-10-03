package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAssessment.MgaStudentTopicProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MgaStudentTopicProgressRepository extends JpaRepository<MgaStudentTopicProgress, Long> {

    interface CompletedByOfferingRow {
        Long getSubjectOfferingId();
        Long getCompletedTopics();
    }

    Optional<MgaStudentTopicProgress> findByMgaStudent_MgaStudentIdAndMgaSubjectOffering_MgaSubjectOfferingIdAndMgaTopic_MgaTopicId(
        Long studentId, Long offeringId, Long topicId
    );

    @Query("""
        select p.mgaTopic.mgaTopicId
        from MgaStudentTopicProgress p
        where p.mgaStudent.mgaStudentId = :sid
          and p.mgaSubjectOffering.mgaSubjectOfferingId = :oid
          and (
                p.completedAt is not null
                or upper(coalesce(p.progressStatus, '')) = 'COMPLETED'
              )
    """)
    Set<Long> findCompletedTopicIds(@Param("sid") Long studentId, @Param("oid") Long offeringId);

    @Query(value = """
        select
          p.mga_subject_offering_id as subjectOfferingId,
          count(distinct p.mga_topic_id) as completedTopics
        from mga_student_topic_progress p
        where p.mga_student_id = :sid
          and coalesce(p.active_status, 'A') = 'A'
          and (
                p.completed_at is not null
                or upper(coalesce(p.progress_status, '')) = 'COMPLETED'
              )
        group by p.mga_subject_offering_id
        """, nativeQuery = true)
    List<CompletedByOfferingRow> countCompletedByOffering(@Param("sid") Long studentId);
}