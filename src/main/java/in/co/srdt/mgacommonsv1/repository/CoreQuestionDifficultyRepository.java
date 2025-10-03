package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAssessment.CoreQuestionDifficulty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoreQuestionDifficultyRepository extends JpaRepository<CoreQuestionDifficulty, Long> {
    Optional<CoreQuestionDifficulty> findByCode(String code);
}