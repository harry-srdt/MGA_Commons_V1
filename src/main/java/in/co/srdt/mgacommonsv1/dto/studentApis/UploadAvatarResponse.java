package in.co.srdt.mgacommonsv1.dto.studentApis;

public record UploadAvatarResponse(
        String username,
        String url,
        String mimeType,
        long sizeBytes,
        String fileName,
        String storagePath
) {}