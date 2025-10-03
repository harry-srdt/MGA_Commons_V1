package in.co.srdt.mgacommonsv1.dto.academicStructure;

public record SaveTopicContentListResponse(
    Long offeringId,
    int topicsProcessed,
    int contentsUpserted,
    int contentsDeactivated,
    int assetsUpserted,
    int assetsDeactivated
) {}