package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAssessment.MgaTopicQuestionPool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MgaTopicQuestionPoolRepository extends JpaRepository<MgaTopicQuestionPool, Long> {
    List<MgaTopicQuestionPool> findByMgaTopic_MgaTopicId(Long topicId);
}