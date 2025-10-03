package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAssessment.MgaAssessmentAttemptAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MgaAssessmentAttemptAnswerRepository extends JpaRepository<MgaAssessmentAttemptAnswer, Long> {
    Optional<MgaAssessmentAttemptAnswer> findByMgaAssessmentAttemptQuestion_MgaAssessmentAttemptQuestionId(Long attemptQuestionId);
}