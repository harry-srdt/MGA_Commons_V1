package in.co.srdt.mgacommonsv1.dto.subjectOfferingManagement;

public record SaveSubjectOfferingResponse(
        Long offeringId,
        Long subjectId,
        String subjectCode,
        String deliveryModeCode,
        String offeringTitle,
        java.time.LocalDate startDate,
        java.time.LocalDate endDate
) {}