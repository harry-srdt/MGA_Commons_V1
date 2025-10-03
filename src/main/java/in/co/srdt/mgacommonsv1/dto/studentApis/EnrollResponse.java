package in.co.srdt.mgacommonsv1.dto.studentApis;

public record EnrollResponse(
        Long studentId,
        Long offeringId,
        String subjectCode,
        String deliveryMode,
        String offeringTitle,
        java.time.LocalDate enrollmentDate,
        String status
) {}