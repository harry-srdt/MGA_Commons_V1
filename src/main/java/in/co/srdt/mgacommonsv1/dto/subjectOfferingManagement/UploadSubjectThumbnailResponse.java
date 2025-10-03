package in.co.srdt.mgacommonsv1.dto.subjectOfferingManagement;

public record UploadSubjectThumbnailResponse(
        /*Long subjectId,*/
        String filename,
        String storedPath,
        long sizeBytes,
        String contentType,
        String url
) {}