package in.co.srdt.mgacommonsv1.dto.subjectOfferingManagement;

import java.util.List;

public record SubjectDetailResponse(
        Long mgaSubjectId,
        String subjectCode,
        String title,
        Integer credits,
        String subjectShortDescription,
        String subjectDescription,
        List<ThumbnailDto> thumbnails,
        List<KloDto> keyLearningObjectives,
        List<UnitDto> units
) {
    public record ThumbnailDto(String fileName, String storagePath) {}
    public record KloDto(Integer displayOrder, String keyLearningObjective) {}
    public record UnitDto(
            Long unitId,
            Integer displaySequence,
            String name,
            String description,
            List<TopicDto> topics
    ) {
        public record TopicDto(
                Long topicId,
                Integer displaySequence,
                String title,
                Integer estimatedMinutes,
                String summaryText,
                String summaryFormat
        ) {}
    }
}