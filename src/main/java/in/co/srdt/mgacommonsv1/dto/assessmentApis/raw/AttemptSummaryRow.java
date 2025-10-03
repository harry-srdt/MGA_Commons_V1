package in.co.srdt.mgacommonsv1.dto.assessmentApis.raw;

public interface AttemptSummaryRow {
    Long getAttemptId();
    java.time.LocalDateTime getEvaluatedAt();
    java.math.BigDecimal getTotalMarks();
    java.math.BigDecimal getObtainedMarks();
    Integer getQuestionCount();
    Integer getCorrectCount();
}