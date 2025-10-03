package in.co.srdt.mgacommonsv1.dto.forum;

public record PostItem(
    Long postId,
    Long parentPostId,
    String body,
    String repliedByName,
    String repliedByType,
    java.sql.Timestamp createdAt
) {}