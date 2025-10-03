package in.co.srdt.mgacommonsv1.dto.studentApis;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StudentSubjectDetail(
        Long subjectOfferingId,
        String subjectCode,
        String subjectTitle,
        String subjectThumbnail,
        String estimatedTime,
        Integer lecturesCount,
        Integer subjectCredits,
        String deliveryMode,
        String facultyName,
        String classCode,
        String termName,
        BigDecimal averageRating,
        Integer ratingCount,
        LocalDate startDate,
        LocalDate endDate,
        Integer percentComplete
) {}