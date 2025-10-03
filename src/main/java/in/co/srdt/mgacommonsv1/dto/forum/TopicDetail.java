package in.co.srdt.mgacommonsv1.dto.forum;

public record TopicDetail(
    Long topicId,
    String title,
    String body,
    String askedByName,
    String askedByType,
    Integer replyCount,
    java.sql.Timestamp lastActivityAt
) {}