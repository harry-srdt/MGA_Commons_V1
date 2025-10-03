package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.dto.studentApis.raw.UnitTopicContentRow;
import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MgaUnitRepository extends JpaRepository<MgaUnit, Long> {
    @Query(value = """
        with tc_choice as (
          select
            tc.mga_topic_id,
            tc.mga_topic_content_id,
            cct.code                 as contentTypeCode,
            coalesce(ma.external_url, '') as externalUrl,
            coalesce(ma.storage_path, '') as storagePath,
            tc.description           as contentDesc,
            row_number() over (
              partition by tc.mga_topic_id
              order by coalesce(tc.is_primary,0) desc,
                       coalesce(tc.display_order,0) asc,
                       tc.mga_topic_content_id asc
            ) as rn
          from mga_topic_content tc
          join core_content_type cct on cct.core_content_type_id = tc.core_content_type_id
          left join mga_media_asset ma on ma.mga_topic_content_id = tc.mga_topic_content_id
          where coalesce(tc.active_status,'A')='A'
        )
        select
          u.mga_unit_id                 as unitId,
          u.display_sequence            as unitSeq,
          u.name                        as unitName,
          u.description                 as unitDesc,

          t.mga_topic_id                as topicId,
          t.display_sequence            as topicSeq,
          t.title                       as topicTitle,
          ts.summary_text               as topicSummary,
          t.estimated_minutes           as topicMinutes,

          tc.mga_topic_content_id       as topicContentId,
          tc.contentTypeCode            as contentTypeCode,
          tc.externalUrl                as externalUrl,
          tc.storagePath                as storagePath,
          tc.contentDesc                as contentDesc
        from mga_subject s
        join mga_unit u on u.mga_subject_id = s.mga_subject_id
        left join mga_topic t on t.mga_unit_id = u.mga_unit_id and coalesce(t.active_status,'A')='A'
        left join mga_topic_summary ts on ts.mga_topic_id = t.mga_topic_id
        left join tc_choice tc on tc.mga_topic_id = t.mga_topic_id and tc.rn = 1
        where s.code = :code
          and coalesce(u.active_status,'A')='A'
        order by u.display_sequence, t.display_sequence
        """, nativeQuery = true)
    List<UnitTopicContentRow> fetchUnitTopicTree(@Param("code") String subjectCode);

    List<MgaUnit> findByMgaSubject_MgaSubjectId(Long subjectId);

    @Query("""
    select u from MgaUnit u
    where u.mgaSubject.mgaSubjectId = :subjectId and coalesce(u.activeStatus,'A')='A'
    order by u.displaySequence, u.mgaUnitId
    """)
    List<MgaUnit> findActiveBySubject(@Param("subjectId") Long subjectId);

    @Query("""
        select u
        from MgaUnit u
        where u.mgaSubject.mgaSubjectId = :mgaSubjectId
          and u.activeStatus = 'A'
        order by u.displaySequence asc
        """)
    List<MgaUnit> findByMgaSubject_MgaSubjectIdOrderByDisplaySequenceAsc(@Param("mgaSubjectId") Long mgaSubjectId);
}