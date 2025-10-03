package in.co.srdt.mgacommonsv1.entity.mgaAssessment;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.AuditableBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaQuestionChoice extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaQuestionChoiceId;

    @ManyToOne
    @JoinColumn(name = "mga_question_id", nullable = false)
    private MgaQuestion mgaQuestion;

    @Column(nullable = false, length = 2000)
    private String choiceText;

    @Column(columnDefinition = "bit(1) default 0")
    private Boolean isCorrect = false;

    private Integer displayOrder = 0;
}