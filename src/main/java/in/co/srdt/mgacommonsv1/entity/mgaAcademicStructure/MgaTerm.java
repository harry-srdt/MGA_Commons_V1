package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaTerm extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaTermId;

    @Column(nullable = false, length = 128)
    private String name;

    private LocalDate startDate;
    private LocalDate endDate;
}
