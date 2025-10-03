package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAssessment.MgaQuestionVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MgaQuestionVersionRepository extends JpaRepository<MgaQuestionVersion, Long> {
    Optional<MgaQuestionVersion> findTopByMgaQuestion_MgaQuestionIdOrderByVersionNumberDesc(Long qid);
}