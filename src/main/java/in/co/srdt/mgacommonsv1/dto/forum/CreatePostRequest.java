package in.co.srdt.mgacommonsv1.dto.forum;

public record CreatePostRequest(
    Long parentPostId,
    String body
) {}