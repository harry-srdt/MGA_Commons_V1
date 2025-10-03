package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaMediaAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MgaMediaAssetRepository extends JpaRepository<MgaMediaAsset, Long> {
    List<MgaMediaAsset> findByMgaTopicContent_MgaTopicContentId(Long topicContentId);

    @Query("""
        select a from MgaMediaAsset a
        where a.mgaTopicContent.mgaTopicContentId in :tcIds
          and coalesce(a.activeStatus,'A')='A'
        order by a.mgaTopicContent.mgaTopicContentId, a.mgaMediaAssetId
    """)
    List<MgaMediaAsset> findActiveByTopicContentIds(@Param("tcIds") List<Long> topicContentIds);
}