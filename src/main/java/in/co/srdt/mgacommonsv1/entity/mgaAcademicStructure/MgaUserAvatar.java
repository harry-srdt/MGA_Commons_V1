package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import in.co.srdt.mgacommonsv1.entity.mgaUserManagement.MgaAuthUserDetails;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(
  name = "mga_user_avatar",
  indexes = {
    @Index(name = "idx_mua_username", columnList = "username"),
    @Index(name = "idx_mua_active_status", columnList = "active_status")
  }
)
public class MgaUserAvatar extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaUserAvatarId;

    // username is PK on MgaAuthUserDetails
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private MgaAuthUserDetails authUser;

    @Column(length = 500)
    private String fileName;

    @Column(length = 1000)
    private String storagePath;

    @Column(length = 255)
    private String mimeType;

    private Long sizeBytes;

    @Column(length = 64)
    private String sha256;

    @Column(columnDefinition = "bit(1) default 1")
    private Boolean isPrimary = true;
}