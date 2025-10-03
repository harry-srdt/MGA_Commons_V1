package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaStudentSubjectEnrollment extends AuditableBase {
    @EmbeddedId
    private MgaStudentSubjectEnrollmentId id;

    @ManyToOne
    @MapsId("mgaStudentId")
    @JoinColumn(name = "mga_student_id", nullable = false)
    private MgaStudent mgaStudent;

    @ManyToOne
    @MapsId("mgaSubjectOfferingId")
    @JoinColumn(name = "mga_subject_offering_id", nullable = false)
    private MgaSubjectOffering mgaSubjectOffering;

    @Column(nullable = false)
    private LocalDate enrollmentDate;

    @Column(length = 1)
    private String status; // 'A' / 'I'

    @Embeddable
    @Data
    @EqualsAndHashCode
    public static class MgaStudentSubjectEnrollmentId implements Serializable {
        private Long mgaStudentId;
        private Long mgaSubjectOfferingId;
    }
}
