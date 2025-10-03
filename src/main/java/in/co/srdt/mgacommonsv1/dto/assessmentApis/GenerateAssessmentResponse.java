package in.co.srdt.mgacommonsv1.dto.assessmentApis;

import java.util.List;

public record GenerateAssessmentResponse(
    Long attemptId,
    Long topicId,
    List<McqItem> questions
) {
    public record McqItem(
        Long attemptQuestionId,
        Long questionId,
        Long questionVersionId,
        int displaySequence,
        String stem,
        String questionType,
        java.math.BigDecimal marks,
        List<McqOption> options
    ) {
        public record McqOption(
            Long choiceId,
            String text
        ) {}
    }
}