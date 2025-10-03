package in.co.srdt.mgacommonsv1.dto.forum;

public record TopicListItem(
    Long topicId,
    String title,
    String body,
    String askedByName,
    String askedByType,
    Integer replyCount,
    java.sql.Timestamp lastActivityAt,
    boolean unread
) {}