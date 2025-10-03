package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class CoreDeliveryMode extends AuditableBase{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coreDeliveryModeId;

    @Column(nullable = false, unique = true, length = 64)
    private String code;

    @Column(length = 255)
    private String description;
}
