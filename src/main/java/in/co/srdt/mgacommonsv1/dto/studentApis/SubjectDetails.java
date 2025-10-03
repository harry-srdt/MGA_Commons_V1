package in.co.srdt.mgacommonsv1.dto.studentApis;

import java.util.List;

public record SubjectDetails(
        String subjectCode,
        Long subjectOfferingId,
        String subjectTitle,
        String subjectThumbnail,
        String subjectOverview,
        String subjectShortDescription,
        List<SubjectKeyLearningObjective> subjectKeyLearningObjectives,
        String estimatedTime,
        Integer lecturesCount,
        Integer subjectCredits,
        String deliveryMode,
        FacultyDetails facultyDetails,
        List<UnitDetails> unitDetails,
        Integer completionPercent,
        Integer completedTopics,
        Integer totalTopics
) {}
