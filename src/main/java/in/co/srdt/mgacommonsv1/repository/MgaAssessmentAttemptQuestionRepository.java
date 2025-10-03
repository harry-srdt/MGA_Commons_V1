package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAssessment.MgaAssessmentAttemptQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MgaAssessmentAttemptQuestionRepository extends JpaRepository<MgaAssessmentAttemptQuestion, Long> {
    List<MgaAssessmentAttemptQuestion> findByMgaAssessmentAttempt_MgaAssessmentAttemptId(Long attemptId);

    @Query(value = """
        select
          q.mga_assessment_attempt_question_id as attemptQuestionId,
          q.display_sequence                   as displaySequence,
          v.stem_snapshot                      as stem,
          coalesce(ans.is_correct,0)           as correctlyAnswered,
          q.mga_question_id                    as questionId,
          q.choice_order_json                  as choiceOrderJson,
          q.marks                              as marks,
          ans.selected_choice_ids_csv          as selectedCsv,
          ans.marks_awarded                    as marksAwarded,
          aq.answer_explanation                as answerExplanation
        from mga_assessment_attempt_question q
        join mga_question_version v on v.mga_question_version_id = q.mga_question_version_id
        JOIN mga_question aq ON q.mga_question_id = aq.mga_question_id
        left join mga_assessment_attempt_answer ans on ans.mga_assessment_attempt_question_id = q.mga_assessment_attempt_question_id
        where q.mga_assessment_attempt_id = :attemptId
        order by q.display_sequence, q.mga_assessment_attempt_question_id
        """, nativeQuery = true)
    List<Object[]> fetchAttemptQuestions(@Param("attemptId") Long attemptId);
}