package in.co.srdt.mgacommonsv1.dto.assessmentApis;

import java.util.List;

public record SubmitAssessmentRequest(
        Long studentId,
        Long attemptId,
        List<AnswerPayload> answers
) {
    public record AnswerPayload(
        Long attemptQuestionId,
        List<Long> selectedChoiceIds
    ) {}
}