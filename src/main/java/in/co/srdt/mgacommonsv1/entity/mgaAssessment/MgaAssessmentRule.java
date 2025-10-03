package in.co.srdt.mgacommonsv1.entity.mgaAssessment;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.AuditableBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaAssessmentRule extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaAssessmentRuleId;

    @ManyToOne
    @JoinColumn(name = "mga_assessment_template_id", nullable = false)
    private MgaAssessmentTemplate mgaAssessmentTemplate;

    @ManyToOne
    @JoinColumn(name = "core_question_difficulty_id", nullable = false)
    private CoreQuestionDifficulty difficulty;

    @ManyToOne
    @JoinColumn(name = "core_question_type_id", nullable = false)
    private CoreQuestionType questionType;

    @Column(nullable = false)
    private Integer questionCount;

    private java.math.BigDecimal marksPerQuestion;
    private java.math.BigDecimal negativePerQuestion;

}