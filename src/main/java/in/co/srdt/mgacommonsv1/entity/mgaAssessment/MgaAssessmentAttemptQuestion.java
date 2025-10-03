package in.co.srdt.mgacommonsv1.entity.mgaAssessment;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.AuditableBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaAssessmentAttemptQuestion extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaAssessmentAttemptQuestionId;

    @ManyToOne
    @JoinColumn(name = "mga_assessment_attempt_id", nullable = false)
    private MgaAssessmentAttempt mgaAssessmentAttempt;

    @ManyToOne
    @JoinColumn(name = "mga_question_id", nullable = false)
    private MgaQuestion mgaQuestion;

    @ManyToOne
    @JoinColumn(name = "mga_question_version_id", nullable = false)
    private MgaQuestionVersion mgaQuestionVersion;

    private Integer displaySequence;

    @ManyToOne
    @JoinColumn(name = "core_question_type_id", nullable = false)
    private CoreQuestionType questionType;

    @ManyToOne
    @JoinColumn(name = "core_question_difficulty_id", nullable = false)
    private CoreQuestionDifficulty difficulty;

    private java.math.BigDecimal marks;
    private java.math.BigDecimal negativeMarks;

    @Column(columnDefinition = "LONGTEXT")
    private String choiceOrderJson;
}