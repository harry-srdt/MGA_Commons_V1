package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAssessment.MgaQuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MgaQuestionChoiceRepository extends JpaRepository<MgaQuestionChoice, Long> {
    List<MgaQuestionChoice> findByMgaQuestion_MgaQuestionIdOrderByDisplayOrderAsc(Long questionId);

    @Query("""
        select c.mgaQuestionChoiceId
        from MgaQuestionChoice c
        where c.mgaQuestion.mgaQuestionId = :qid and coalesce(c.isCorrect,false) = true
        order by c.mgaQuestionChoiceId
    """)
    java.util.Set<Long> findCorrectChoiceIdsForQuestion(@Param("qid") Long questionId);

    @Query(value = """
        select c.mga_question_choice_id,
                       c.choice_text,
                       c.is_correct
        from mga_question_choice c
        where c.mga_question_id = :questionId
            AND COALESCE(c.active_status, 'A') = 'A'
        order by c.display_order, c.mga_question_choice_id
        """, nativeQuery = true)
    List<Object[]> findAllForQuestion(@Param("questionId") Long questionId);
}