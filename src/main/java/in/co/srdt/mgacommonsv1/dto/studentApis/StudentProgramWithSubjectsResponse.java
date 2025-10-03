package in.co.srdt.mgacommonsv1.dto.studentApis;


import java.util.List;

public record StudentProgramWithSubjectsResponse(
        String rollNumber,
        String name,
        String erpId,
        String email,
        String programName,
        List<StudentSubjectDetail> activeSubjects
) {}