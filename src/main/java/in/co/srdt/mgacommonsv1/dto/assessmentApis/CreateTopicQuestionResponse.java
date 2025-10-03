package in.co.srdt.mgacommonsv1.dto.assessmentApis;

import java.util.List;

public record CreateTopicQuestionResponse(
        Long topicId,
        Long questionId,
        Long questionVersionId,
        List<ChoiceOut> choices,
        Long topicQuestionPoolId
) {
    public record ChoiceOut(
            Long choiceId,
            String text,
            Boolean correct, 
            Integer displayOrder
    ) {}
}