package in.co.srdt.mgacommonsv1.entity.mgaAssessment;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.AuditableBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaAssessmentAttemptAnswer extends AuditableBase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaAssessmentAttemptAnswerId;

    @ManyToOne
    @JoinColumn(name = "mga_assessment_attempt_question_id", nullable = false)
    private MgaAssessmentAttemptQuestion mgaAssessmentAttemptQuestion;

    @Column(columnDefinition = "LONGTEXT")
    private String selectedChoiceIdsCsv;

    private java.math.BigDecimal marksAwarded;

    @Column(columnDefinition = "bit(1) default 0")
    private Boolean isCorrect = false;
}