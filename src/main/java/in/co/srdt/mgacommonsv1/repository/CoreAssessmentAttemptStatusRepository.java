package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAssessment.CoreAssessmentAttemptStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoreAssessmentAttemptStatusRepository extends JpaRepository<CoreAssessmentAttemptStatus, Long> {
    Optional<CoreAssessmentAttemptStatus> findByCode(String code);
}