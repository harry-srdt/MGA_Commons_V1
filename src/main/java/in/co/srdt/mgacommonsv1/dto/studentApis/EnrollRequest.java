package in.co.srdt.mgacommonsv1.dto.studentApis;

public record EnrollRequest(
        Long studentId,
        Long offeringId
) {}