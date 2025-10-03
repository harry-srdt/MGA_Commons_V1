package in.co.srdt.mgacommonsv1.dto.assessmentApis;

import java.util.List;

public record SubjectTopicAssessmentSummaryResponse(
        List<TopicAssessmentSummary> topics
) {
    public record TopicAssessmentSummary(
            Long topicId,
            String topicTitle,
            Long evaluatedAttempts,              // count of evaluated attempts
            java.math.BigDecimal lastObtainedMarks // 0 if none
    ) {}
}