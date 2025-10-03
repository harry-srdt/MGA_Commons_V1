package in.co.srdt.mgacommonsv1.entity.mgaAssessment;

import java.math.BigDecimal;

public record AssessmentTemplateSummary(
    String title,
    String instructions,
    BigDecimal maxMarks,
    BigDecimal passMarks,
    BigDecimal passPercent,
    int currentAttemptNumber
) {}