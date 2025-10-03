package in.co.srdt.mgacommonsv1.dto.studentApis;

public record TopicDetails(
        Long topicId,
        Integer displaySequence,
        String title,
        String summaryText,
        Integer estimatedMinutes,
        String estimatedTime,
        TopicContent topicContent,
        Boolean completed
) {}
