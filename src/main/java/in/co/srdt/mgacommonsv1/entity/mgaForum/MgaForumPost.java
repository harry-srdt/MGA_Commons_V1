package in.co.srdt.mgacommonsv1.entity.mgaForum;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.AuditableBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaForumPost extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaForumPostId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mga_forum_topic_id")
    private MgaForumTopic mgaForumTopic;

    @ManyToOne
    @JoinColumn(name = "parent_post_id")
    private MgaForumPost parentPost;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String body;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrincipalType repliedByType;

    @Column(nullable = false)
    private Long repliedById;

    @Column(nullable = false)
    private String repliedByNameCache;
}