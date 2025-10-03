package in.co.srdt.mgacommonsv1.dto.subjectOfferingManagement;

import java.util.List;

public record SaveSubjectTreeRequest(
        String subjectCode,
        String title,
        Integer credits,

        // SubjectAttributes (one row)
        String subjectShortDescription,
        String subjectDescription,

        // Subject thumbnails (overwrite list)
        List<SubjectThumbnailIn> thumbnails,

        // KLOs (overwrite list)
        List<KloIn> keyLearningObjectives,

        // Units & Topics (upsert)
        List<UnitIn> units
) {
    public record SubjectThumbnailIn(
            String fileName,
            String storagePath
    ) { }

    public record KloIn(
            Integer displayOrder,
            String keyLearningObjective
    ) { }

    public record UnitIn(
            Long unitId,
            Integer displaySequence,
            String name,
            String description,
            List<TopicIn> topics
    ) {}

    public record TopicIn(
            Long topicId,
            Integer displaySequence,
            String title,
            Integer estimatedMinutes,
            String summaryText,
            String summaryFormat
    ) {}
}