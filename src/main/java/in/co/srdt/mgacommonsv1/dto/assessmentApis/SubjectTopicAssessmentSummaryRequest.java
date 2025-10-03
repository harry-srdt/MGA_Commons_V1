package in.co.srdt.mgacommonsv1.dto.assessmentApis;

public record SubjectTopicAssessmentSummaryRequest(
        String subjectCode,
        Long studentId
) {}