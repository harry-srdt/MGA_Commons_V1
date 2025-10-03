package in.co.srdt.mgacommonsv1.dto.studentApis.raw;

public interface UnitTopicContentRow {
    Long getUnitId();
    Integer getUnitSeq();
    String getUnitName();
    String getUnitDesc();
    Integer getEstimatedTime();
    Integer getLecturesCount();

    Long getTopicId();
    Integer getTopicSeq();
    String getTopicTitle();
    String getTopicSummary();
    Integer getTopicMinutes();

    Long getTopicContentId();
    String getContentTypeCode();
    String getExternalUrl();
    String getStoragePath();
    String getContentDesc();
}