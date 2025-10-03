package in.co.srdt.mgacommonsv1.dto.studentApis;

public record TopicContent(
        String topiccontentid,
        String topicid,
        String contentType,
        String externalUrl,
        String storagePath,
        String description
) {}
