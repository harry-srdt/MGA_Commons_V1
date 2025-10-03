package in.co.srdt.mgacommonsv1.dto.assessmentApis;

public record SubmitAssessmentResponse(
    Long attemptId,
    Long topicId,
    java.math.BigDecimal totalMarks,
    java.math.BigDecimal obtainedMarks,
    String attemptStatus,
    String examResult
) {}