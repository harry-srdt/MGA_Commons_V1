package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaTopic extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaTopicId;

    @ManyToOne
    @JoinColumn(name = "mga_unit_id", nullable = false)
    private MgaUnit mgaUnit;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer estimatedMinutes;

    @Column(nullable = false)
    private Integer displaySequence;
}
