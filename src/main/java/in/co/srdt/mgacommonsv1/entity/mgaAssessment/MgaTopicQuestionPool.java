package in.co.srdt.mgacommonsv1.entity.mgaAssessment;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.AuditableBase;
import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaTopic;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaTopicQuestionPool extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaTopicQuestionPoolId;

    @ManyToOne
    @JoinColumn(name = "mga_topic_id", nullable = false)
    private MgaTopic mgaTopic;

    @ManyToOne @JoinColumn(name = "mga_question_id", nullable = false)
    private MgaQuestion mgaQuestion;

    private Integer weight;
}