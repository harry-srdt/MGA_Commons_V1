package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import in.co.srdt.mgacommonsv1.entity.mgaUserManagement.MgaAuthUserDetails;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaStudent extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaStudentId;

    @Column(nullable = false, length = 64)
    private String firstName;

    @Column(nullable = false, length = 64)
    private String lastName;

    @Column(length = 255, unique = true)
    private String email;

    @Column(length = 64, unique = true)
    private String erpId;

    @Column(length = 64, unique = true)
    private String rollNumber;

    @Column(length = 1)
    private String enrollmentStatus;

    private LocalDate admitDate;

    @OneToMany(mappedBy = "mgaStudent")
    private List<MgaStudentProgram> mgaStudentPrograms;

    @OneToOne
    @JoinColumn(name = "rollNumber", referencedColumnName = "username", insertable = false, updatable = false)
    private MgaAuthUserDetails mgaAuthUserDetails;
}
