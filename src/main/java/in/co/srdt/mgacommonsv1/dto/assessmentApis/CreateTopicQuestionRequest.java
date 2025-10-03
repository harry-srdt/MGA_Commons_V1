package in.co.srdt.mgacommonsv1.dto.assessmentApis;

import java.util.List;

public record CreateTopicQuestionRequest(
        Long topicId,
        String questionType,
        String difficultyCode,
        String stem,
        String explanation,
        List<OptionPayload> options
) {
    public record OptionPayload(
        String text,
        Boolean correct
    ) {}
}