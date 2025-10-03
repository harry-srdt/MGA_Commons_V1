package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaTopicContent extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaTopicContentId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "mga_topic_id")
    private MgaTopic mgaTopic;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "core_content_type_id")
    private CoreContentType coreContentType;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer displayOrder = 0;

    @Column(columnDefinition = "bit(1) default 0")
    private Boolean isPrimary = false;

    @Column(length = 30)
    private String publishStatus = "DRAFT";

    private java.time.LocalDateTime publishedAt;

    @OneToMany(mappedBy = "mgaTopicContent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MgaMediaAsset> mediaAssets;
}
