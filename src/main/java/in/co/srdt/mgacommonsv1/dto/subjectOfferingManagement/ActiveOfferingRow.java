package in.co.srdt.mgacommonsv1.dto.subjectOfferingManagement;

public interface ActiveOfferingRow {
    Long getSubjectOfferingId();
    String getSubjectCode();
    String getSubjectTitle();
    String getSubjectThumbnail();
    String getEstimatedTime();
    Integer getLecturesCount();
    Integer getSubjectCredits();
    String getDeliveryMode();
    String getFacultyName();
    String getClassCode();
    String getTermName();
    java.time.LocalDate getStartDate();
    java.time.LocalDate getEndDate();
    java.math.BigDecimal getAverageRating();
    Integer getRatingCount();
}