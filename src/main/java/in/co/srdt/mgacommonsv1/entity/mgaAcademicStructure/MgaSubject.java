package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaSubject extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaSubjectId;

    @Column(nullable = false, unique = true, length = 64)
    private String code;

    @Column(nullable = false, length = 255)
    private String title;

    private Integer credits;

    @OneToMany(mappedBy = "mgaSubject", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<MgaSubjectThumbnail> mgaSubjectThumbnails;
}
