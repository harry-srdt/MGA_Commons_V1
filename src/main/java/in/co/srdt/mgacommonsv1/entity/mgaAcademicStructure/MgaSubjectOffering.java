package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaSubjectOffering extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaSubjectOfferingId;

    @ManyToOne
    @JoinColumn(name = "mga_subject_id", nullable = false)
    private MgaSubject mgaSubject;

    @ManyToOne
    @JoinColumn(name = "core_delivery_mode_id", nullable = false)
    private CoreDeliveryMode deliveryMode; // SELF_PACED / FACULTY_DRIVEN

    // For FACULTY_DRIVEN; nullable for SELF_PACED
    @ManyToOne
    @JoinColumn(name = "mga_class_ps_id")
    private MgaClassPs mgaClassPs;

    @ManyToOne
    @JoinColumn(name = "mga_faculty_id")
    private MgaFaculty mgaFaculty;

    @ManyToOne
    @JoinColumn(name = "mga_term_id")
    private MgaTerm mgaTerm;

    @Column(nullable = false, length = 255)
    private String offeringTitle;

    @Column(columnDefinition = "TEXT")
    private String offeringDescription;

    private Integer enrollmentLimit;

    // For FACULTY_DRIVEN; nullable for SELF_PACED
    private LocalDate startDate;

    // For FACULTY_DRIVEN; nullable for SELF_PACED
    private LocalDate endDate;
}
