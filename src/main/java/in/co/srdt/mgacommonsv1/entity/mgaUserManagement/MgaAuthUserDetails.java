package in.co.srdt.mgacommonsv1.entity.mgaUserManagement;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaStudent;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class MgaAuthUserDetails {

    @Id
    @Column(name = "username", nullable = false, length = 255)
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "user_type")
    private String userType;

    @OneToOne(mappedBy = "mgaAuthUserDetails")
    private MgaStudent mgaStudent;
}