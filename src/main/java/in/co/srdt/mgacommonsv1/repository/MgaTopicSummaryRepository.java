package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaTopicSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MgaTopicSummaryRepository extends JpaRepository<MgaTopicSummary, Long> {
    Optional<MgaTopicSummary> findByMgaTopic_MgaTopicId(Long topicId);

    List<MgaTopicSummary> findByMgaTopic_MgaTopicIdIn(Collection<Long> topicIds);
}