package in.co.srdt.mgacommonsv1.dto.subjectOfferingManagement;

import java.time.LocalDateTime;

public record SubjectListItem(
        String code,
        String title,
        LocalDateTime updatedAt,
        String activeStatus
) {}