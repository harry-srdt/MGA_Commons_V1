package in.co.srdt.mgacommonsv1.dto.assessmentApis.raw;

public interface TopicAssessmentProgressRow {
    Long getTopicId();
    String getTopicTitle();
    Long getEvaluatedAttempts();
    java.math.BigDecimal getLastObtainedMarks();
}