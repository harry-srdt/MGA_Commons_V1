package in.co.srdt.mgacommonsv1.dto.subjectOfferingManagement;

import java.time.LocalDateTime;

public record SubjectListItem(
        Long subjectId,
        String code,
        String title,
        LocalDateTime updatedAt,
        String activeStatus
) {}