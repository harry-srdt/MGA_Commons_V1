package in.co.srdt.mgacommonsv1.entity.mgaAssessment;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.AuditableBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaQuestionVersion extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaQuestionVersionId;

    @ManyToOne @JoinColumn(name = "mga_question_id", nullable = false)
    private MgaQuestion mgaQuestion;

    @ManyToOne @JoinColumn(name = "core_question_type_id", nullable = false)
    private CoreQuestionType questionType;

    @ManyToOne
    @JoinColumn(name = "core_question_difficulty_id", nullable = false)
    private CoreQuestionDifficulty difficulty;

    private Integer versionNumber;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String stemSnapshot;
}
