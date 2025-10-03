package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaProgram extends AuditableBase{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaProgramId;

    @ManyToOne
    @JoinColumn(name = "mga_career_id", nullable = false)
    private MgaCareer mgaCareer;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(length = 64)
    private String startTerm;

    @Column(length = 64)
    private String endTerm;
}
