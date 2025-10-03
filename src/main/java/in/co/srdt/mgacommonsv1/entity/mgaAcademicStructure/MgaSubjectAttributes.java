package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaSubjectAttributes extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaSubjectAttributesId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mga_subject_id")
    private MgaSubject mgaSubject;

    @Column(columnDefinition = "TEXT")
    private String subjectShortDescription;

    @Column(columnDefinition = "TEXT")
    private String subjectDescription;
}
