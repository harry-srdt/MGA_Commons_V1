package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAssessment.CoreQuestionDifficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CoreQuestionDifficultyRepository extends JpaRepository<CoreQuestionDifficulty, Long> {
    Optional<CoreQuestionDifficulty> findByCode(String code);

    @Query("select qd from CoreQuestionDifficulty qd where coalesce(qd.activeStatus, 'A') = 'A'")
    List<CoreQuestionDifficulty> findAllActive();
}