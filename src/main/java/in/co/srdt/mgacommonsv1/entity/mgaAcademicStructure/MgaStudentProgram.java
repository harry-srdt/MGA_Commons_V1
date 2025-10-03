package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaStudentProgram extends AuditableBase {
    @EmbeddedId
    private MgaStudentProgramId id;

    @ManyToOne
    @MapsId("mgaStudentId")
    @JoinColumn(name = "mga_student_id", nullable = false)
    private MgaStudent mgaStudent;

    @ManyToOne
    @MapsId("mgaProgramId")
    @JoinColumn(name = "mga_program_id", nullable = false)
    private MgaProgram mgaProgram;

    private LocalDate admitDate;

    @Embeddable
    @Data @EqualsAndHashCode
    public static class MgaStudentProgramId implements Serializable {
        private Long mgaStudentId;
        private Long mgaProgramId;
    }
}
