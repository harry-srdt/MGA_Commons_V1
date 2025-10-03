package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAssessment.MgaAssessmentTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MgaAssessmentTemplateRepository extends JpaRepository<MgaAssessmentTemplate, Long> {
    @Query(value = """
        select *
        from mga_assessment_template t
        where t.mga_topic_id = :topicId
        order by
             coalesce(t.updated_at, t.created_at) desc,
             t.mga_assessment_template_id desc
        limit 1
    """, nativeQuery = true)
    Optional<MgaAssessmentTemplate> resolveOneNative(
            @Param("topicId") Long topicId
    );

    @Query("""
        select t
        from MgaAssessmentTemplate t
        where t.mgaTopic.mgaTopicId = :topicId
          and coalesce(t.activeStatus, 'A') = 'A'
        order by t.createdAt desc, t.mgaAssessmentTemplateId desc
    """)
    List<MgaAssessmentTemplate> findActiveByTopicOrder(@Param("topicId") Long topicId);

    default Optional<MgaAssessmentTemplate> findLatestActiveForTopic(Long topicId) {
        var list = findActiveByTopicOrder(topicId);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }
}