package in.co.srdt.mgacommonsv1.dto.studentApis.raw;

import java.math.BigDecimal;

public interface RawSubjectRow {
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
    BigDecimal getAverageRating();
    Integer getRatingCount();
    java.sql.Date getStartDate();
    java.sql.Date getEndDate();
}