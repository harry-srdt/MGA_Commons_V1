package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import in.co.srdt.mgacommonsv1.entity.mgaUserManagement.MgaAuthUserDetails;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(
        name = "mga_faculty",
        indexes = {
                @Index(name = "idx_mga_faculty_username", columnList = "username"),
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_mga_faculty_username", columnNames = {"username"})
        }
)
public class MgaFaculty extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaFacultyId;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(length = 255, unique = true)
    private String email;

    @Column(length = 20)
    private String phone;

    private String specialization;

    @Column(columnDefinition = "TEXT")
    private String about;

    @Column(name = "username", length = 255, nullable = false)
    private String username;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username", insertable=false, updatable=false)
    private MgaAuthUserDetails mgaAuthUserDetails;
}
