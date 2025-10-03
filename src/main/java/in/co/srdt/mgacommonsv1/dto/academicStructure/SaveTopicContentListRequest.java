package in.co.srdt.mgacommonsv1.dto.academicStructure;

import java.util.List;

public record SaveTopicContentListRequest(
    Long offeringId,
    List<TopicContentsUpsert> items,
    Boolean deleteMissing
) {
    public record TopicContentsUpsert(
        Long topicId,
        List<ContentUpsert> contents
    ) {}

    public record ContentUpsert(
        Long topicContentId,
        String contentTypeCode,
        String title,
        String description,
        Integer displayOrder,
        Boolean isPrimary,
        String publishStatus,
        List<AssetUpsert> assets
    ) {}

    public record AssetUpsert(
            Long mediaAssetId,
            String storageProviderCode,
            String fileName,
            String storagePath,
            String externalUrl,
            Integer durationSeconds
    ) {}
}