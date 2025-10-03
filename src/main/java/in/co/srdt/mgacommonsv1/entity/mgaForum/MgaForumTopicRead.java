package in.co.srdt.mgacommonsv1.entity.mgaForum;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.AuditableBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "mga_forum_topic_read",
       uniqueConstraints = @UniqueConstraint(name = "uq_read",
       columnNames = {"mga_forum_topic_id","reader_type","reader_id"}))
public class MgaForumTopicRead extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaForumTopicReadId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mga_forum_topic_id")
    private MgaForumTopic mgaForumTopic;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrincipalType readerType;

    @Column(nullable = false)
    private Long readerId;

    private Long lastReadPostId;
    private java.sql.Timestamp lastReadAt;
}