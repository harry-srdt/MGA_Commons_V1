package in.co.srdt.mgacommonsv1.dto.studentApis;

public record FacultyDetails(
        String facultyId,
        String facultyName,
        String email,
        String specialization,
        String about,
        Long rating,
        Long ratingCount,
        Long courseCount
) {
}
