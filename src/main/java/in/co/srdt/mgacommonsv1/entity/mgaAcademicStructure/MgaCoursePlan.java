package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaCoursePlan extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaCoursePlanId;

    @ManyToOne
    @JoinColumn(name = "mga_subject_offering_id", nullable = false)
    private MgaSubjectOffering mgaSubjectOffering;

    @ManyToOne
    @JoinColumn(name = "mga_faculty_id")
    private MgaFaculty mgaFaculty;

    @Column(nullable = false, length = 255)
    private String planName;

    @Column(length = 1)
    private String status;
}
