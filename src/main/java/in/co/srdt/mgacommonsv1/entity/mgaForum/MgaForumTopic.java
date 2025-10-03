package in.co.srdt.mgacommonsv1.entity.mgaForum;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.AuditableBase;
import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaSubjectOffering;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaForumTopic extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaForumTopicId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mga_subject_offering_id")
    private MgaSubjectOffering mgaSubjectOffering;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String body;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrincipalType askedByType;

    @Column(nullable = false)
    private Long askedById;

    @Column(nullable = false)
    private String askedByNameCache;

    private Integer replyCount = 0;

    @Column(nullable = false)
    private java.sql.Timestamp lastActivityAt;
}
