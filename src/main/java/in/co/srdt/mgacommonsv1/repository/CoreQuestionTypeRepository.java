package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAssessment.CoreQuestionDifficulty;
import in.co.srdt.mgacommonsv1.entity.mgaAssessment.CoreQuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CoreQuestionTypeRepository extends JpaRepository<CoreQuestionType, Long> {
    Optional<CoreQuestionType> findByCode(String code);

    @Query("select qt from CoreQuestionType qt where coalesce(qt.activeStatus, 'A') = 'A'")
    List<CoreQuestionType> findAllActive();
}