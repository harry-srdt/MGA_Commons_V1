package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.dto.studentApis.raw.SubjectBasicsRow;
import in.co.srdt.mgacommonsv1.dto.academicStructure.SubjectBasicRow;
import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaSubject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MgaSubjectRepository extends JpaRepository<MgaSubject, Long> {
    Optional<MgaSubject> findByCode(String code);

    Optional<MgaSubject> findByCodeIgnoreCase(String code);

    @Query(value = """
        select
            s.code                                                  as subjectCode,
            mso.mga_subject_offering_id								as subjectOfferingId,
            s.title                                                 as subjectTitle,
            s.credits                                               as subjectCredits,
            concat(mst.storage_path, mst.file_name)                 as subjectThumbnail,
            sa.subject_description                                  as subjectOverview,
            sa.subject_short_description                            as subjectShortDescription
        from mga_subject s
        LEFT JOIN mga_subject_attributes sa ON s.mga_subject_id = sa.mga_subject_id
        LEFT JOIN mga_subject_thumbnail mst ON mst.mga_subject_id = s.mga_subject_id
        LEFT JOIN mga_subject_offering mso ON mso.mga_subject_id = s.mga_subject_id
        where s.code = :code
            and coalesce(s.active_status,'A') = 'A'
            and coalesce(sa.active_status,'A') = 'A'
            and coalesce(mst.active_status,'A') = 'A'
            and coalesce(mso.active_status,'A') = 'A'
        limit 1
        """, nativeQuery = true)
    SubjectBasicsRow findSubjectBasics(@Param("code") String subjectCode);

    // Full list (active only), ordered by title
    @Query("""
        select s.mgaSubjectId as mgaSubjectId, s.code as code, s.title as title
        from MgaSubject s
        where coalesce(s.activeStatus, 'A') = 'A'
        order by s.title asc
    """)
    List<SubjectBasicRow> findAllBasics();

    @Query("""
        select s
        from MgaSubject s
        where (:code  is null or lower(s.code)  like lower(concat('%', :code,  '%')))
          and (:title is null or lower(s.title) like lower(concat('%', :title, '%')))
        """)
    Page<MgaSubject> searchSubjects(@Param("code") String code,
                                    @Param("title") String title,
                                    Pageable pageable);
}