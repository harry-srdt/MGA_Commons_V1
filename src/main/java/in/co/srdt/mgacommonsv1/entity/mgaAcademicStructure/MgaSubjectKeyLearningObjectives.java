package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaSubjectKeyLearningObjectives extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaSubjectKeyLearningObjectivesId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mga_subject_id")
    private MgaSubject mgaSubject;

    @Column(length = 2000)
    private String keyLearningObjective;

    private Integer displayOrder;
}
