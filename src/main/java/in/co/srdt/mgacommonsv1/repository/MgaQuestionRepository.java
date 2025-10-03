package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAssessment.MgaQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MgaQuestionRepository extends JpaRepository<MgaQuestion, Long> {}