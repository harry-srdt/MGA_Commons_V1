package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaClassPs extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaClassPsId;

    @Column(nullable = false, length = 64, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "mga_program_id", nullable = false)
    private MgaProgram mgaProgram;

    @ManyToOne
    @JoinColumn(name = "mga_term_id", nullable = false)
    private MgaTerm mgaTerm;
}
