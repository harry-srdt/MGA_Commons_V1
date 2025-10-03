package in.co.srdt.mgacommonsv1.dto.subjectOfferingManagement;

public record SaveSubjectOfferingRequest(
        Long offeringId,
        String subjectCode,
        String deliveryModeCode,
        String offeringTitle,
        String offeringDescription,
        Integer enrollmentLimit,

        Long facultyId,
        Long classPsId,
        Long termId,

        java.time.LocalDate startDate,
        java.time.LocalDate endDate
) {}