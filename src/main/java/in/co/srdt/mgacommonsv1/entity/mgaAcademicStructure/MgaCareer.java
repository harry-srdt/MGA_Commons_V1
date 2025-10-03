package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaCareer extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaCareerId;

    @Column(nullable = false, unique = true, length = 128)
    private String name;

    @Column(nullable = false,  unique = true, length = 128)
    private String description;
}
