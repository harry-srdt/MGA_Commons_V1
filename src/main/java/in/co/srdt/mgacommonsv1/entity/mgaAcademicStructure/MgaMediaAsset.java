package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaMediaAsset extends AuditableBase{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaMediaAssetId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "mga_topic_content_id")
    private MgaTopicContent mgaTopicContent;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "core_storage_provider_id")
    private CoreStorageProvider coreStorageProvider;

    @Column(length = 500)
    private String fileName;

    @Column(length = 1000)
    private String storagePath;

    @Column(length = 1000)
    private String externalUrl;

    private Integer durationSeconds;
}
