package in.co.srdt.mgacommonsv1.entity.mgaAssessment;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.AuditableBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaQuestion extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaQuestionId;

    @ManyToOne
    @JoinColumn(name = "core_question_type_id", nullable = false)
    private CoreQuestionType questionType;

    @ManyToOne @JoinColumn(name = "core_question_difficulty_id", nullable = false)
    private CoreQuestionDifficulty difficulty;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String stem;

    private Integer currentVersion = 1;

    @Column(columnDefinition = "LONGTEXT")
    private String answerExplanation;
}