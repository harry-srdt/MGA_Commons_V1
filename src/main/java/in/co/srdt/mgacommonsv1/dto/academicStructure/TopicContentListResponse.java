package in.co.srdt.mgacommonsv1.dto.academicStructure;

import java.util.List;

public record TopicContentListResponse(
    Long offeringId,
    Long subjectId,
    String subjectCode,
    List<TopicWithContents> topics
) {
    public record TopicWithContents(
        Long unitId,
        Integer unitSeq,
        String unitName,
        Long topicId,
        Integer topicSeq,
        String topicTitle,
        Integer estimatedMinutes,
        List<ContentItem> contents
    ) {}

    public record ContentItem(
        Long topicContentId,
        String contentTypeCode,
        String title,
        String description,
        Integer displayOrder,
        Boolean isPrimary,
        String publishStatus,
        java.time.LocalDateTime publishedAt,
        List<AssetItem> assets
    ) {}

    public record AssetItem(
            Long mediaAssetId,
            String storageProviderCode,
            String fileName,
            String storagePath,
            String externalUrl,
            Integer durationSeconds
    ) {}
}