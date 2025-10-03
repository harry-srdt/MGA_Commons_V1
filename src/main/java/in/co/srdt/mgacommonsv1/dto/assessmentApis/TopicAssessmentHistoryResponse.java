package in.co.srdt.mgacommonsv1.dto.assessmentApis;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.List;

public record TopicAssessmentHistoryResponse(
        List<AttemptDetails> attempts
) {
    public record AttemptDetails(
            Long attemptId,
            @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
            java.time.LocalDateTime assessmentDate,
            java.math.BigDecimal totalMarks,
            java.math.BigDecimal obtainedMarks,
            Integer questionCount,
            Integer correctCount,
            List<QuestionDetails> questions
    ) {}

    public record QuestionDetails(
            Long attemptQuestionId,
            Integer sequence,
            String stem,
            Boolean correctlyAnswered,
            BigDecimal marks,
            BigDecimal marksAwarded,
            List<OptionOut> options,
            List<Long> correctOptionIds,
            String answerExplanation
    ) {}

    public record OptionOut(
            Long choiceId,
            String text,
            Boolean selected,
            boolean correct
    ) {}
}