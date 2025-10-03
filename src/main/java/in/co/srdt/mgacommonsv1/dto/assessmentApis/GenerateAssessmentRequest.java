package in.co.srdt.mgacommonsv1.dto.assessmentApis;

public record GenerateAssessmentRequest(
    Long topicId,
    Long offeringId,
    Long studentId,
    boolean persistAttempt
) {}