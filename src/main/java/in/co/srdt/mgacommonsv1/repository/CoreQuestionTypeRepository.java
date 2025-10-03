package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAssessment.CoreQuestionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoreQuestionTypeRepository extends JpaRepository<CoreQuestionType, Long> {
    Optional<CoreQuestionType> findByCode(String code);
}