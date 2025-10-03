package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaProgramSubject extends AuditableBase {
    @EmbeddedId
    private MgaProgramSubjectId id;

    @ManyToOne
    @MapsId("mgaProgramId")
    @JoinColumn(name = "mga_program_id", nullable = false)
    private MgaProgram mgaProgram;

    @ManyToOne @MapsId("mgaSubjectId")
    @JoinColumn(name = "mga_subject_id", nullable = false)
    private MgaSubject mgaSubject;

    @Embeddable @Data @EqualsAndHashCode
    public static class MgaProgramSubjectId implements Serializable {
        private Long mgaProgramId;
        private Long mgaSubjectId;
    }
}
