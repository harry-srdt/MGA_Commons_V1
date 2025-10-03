package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaTopicContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MgaTopicContentRepository extends JpaRepository<MgaTopicContent, Long> {

    List<MgaTopicContent> findByMgaTopic_MgaTopicId(Long topicId);

    @Query("""
        select c from MgaTopicContent c
        where c.mgaTopic.mgaTopicId in :topicIds and coalesce(c.activeStatus,'A')='A'
        order by c.mgaTopic.mgaUnit.displaySequence, c.mgaTopic.displaySequence, c.displayOrder, c.mgaTopicContentId
    """)
    List<MgaTopicContent> findActiveByTopicIds(@Param("topicIds") List<Long> topicIds);

}