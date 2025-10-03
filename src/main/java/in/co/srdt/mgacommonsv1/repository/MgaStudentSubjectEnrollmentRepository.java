package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.dto.studentApis.raw.RawSubjectRow;
import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaStudentSubjectEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MgaStudentSubjectEnrollmentRepository extends JpaRepository<MgaStudentSubjectEnrollment, MgaStudentSubjectEnrollment.MgaStudentSubjectEnrollmentId> {
    List<MgaStudentSubjectEnrollment> findById_MgaStudentId(Long mgaStudentId);
    List<MgaStudentSubjectEnrollment> findById_MgaSubjectOfferingId(Long mgaSubjectOfferingId);
    @Query(value = """
        SELECT
            so.mga_subject_offering_id  AS subjectOfferingId,
            subj.code                   AS subjectCode,
            subj.title                  AS subjectTitle,
            concat(mst.storage_path, mst.file_name)   AS subjectThumbnail,
            concat(concat(FLOOR(vstr.total_minutes / 60), ' Hours '), concat(MOD(vstr.total_minutes, 60), ' Minutes')) AS estimatedTime,
            vstr.topic_count            AS lecturesCount,
            subj.credits                AS subjectCredits,
            dm.code                     AS deliveryMode,
            fac.name                    AS facultyName,
            cls.code                    AS classCode,
            term.name                   AS termName,
            srs.avg_rating			    AS averageRating,
            srs.ratings_count           AS ratingCount,
            so.start_date               AS startDate,
            so.end_date                 AS endDate
        FROM mga_student_subject_enrollment enr
        JOIN mga_student s ON enr.mga_student_id = s.mga_student_id
        JOIN mga_subject_offering so ON enr.mga_subject_offering_id = so.mga_subject_offering_id
        JOIN mga_subject subj ON so.mga_subject_id = subj.mga_subject_id
        JOIN core_delivery_mode dm ON so.core_delivery_mode_id = dm.core_delivery_mode_id
        LEFT JOIN mga_faculty fac ON so.mga_faculty_id = fac.mga_faculty_id
        LEFT JOIN mga_class_ps cls ON so.mga_class_ps_id = cls.mga_class_ps_id
        LEFT JOIN mga_term term ON so.mga_term_id = term.mga_term_id
        LEFT JOIN mga_subject_rating_summary srs ON subj.mga_subject_id = srs.mga_subject_id
        LEFT JOIN mga_subject_thumbnail mst ON subj.mga_subject_id = mst.mga_subject_id
        LEFT JOIN v_subject_time_rollup vstr ON subj.mga_subject_id = vstr.mga_subject_id
        WHERE s.roll_number = :rollNumber
        AND COALESCE(enr.active_status, 'A') = 'A'
        AND COALESCE(so.active_status, 'A') = 'A'
        AND COALESCE(mst.active_status, 'A') = 'A'
    """, nativeQuery = true)
    List<RawSubjectRow> findActiveSubjectsByRoll(@Param("rollNumber") String rollNumber);

    // Find an enrollment (active or inactive)
    Optional<MgaStudentSubjectEnrollment> findByMgaStudent_MgaStudentIdAndMgaSubjectOffering_MgaSubjectOfferingId(Long studentId, Long offeringId);

    // Count active enrollments for capacity checks
    // long countByMgaSubjectOffering_MgaSubjectOfferingIdAndStatusIn(Long offeringId, Collection<String> statuses);

    @Query("""
        select count(e)
        from MgaStudentSubjectEnrollment e
        where e.mgaSubjectOffering.mgaSubjectOfferingId = :oid
          and coalesce(e.activeStatus, 'A') = 'A'
          and (e.status = 'A' or e.status is null)
    """)
    long countActiveEnrollments(@Param("oid") Long offeringId);

    // Check if an active enrollment exists for a student in a subject offering
    @Query(value = """
        SELECT CASE WHEN EXISTS(
            SELECT 1
            FROM mga_student_subject_enrollment e
            WHERE e.mga_student_id = :studentId
              AND e.mga_subject_offering_id = :offeringId
              AND COALESCE(e.status, 'A') = 'A'
        ) THEN 1 ELSE 0 END
        """, nativeQuery = true)
    int existsActiveEnrollment(@Param("studentId") Long studentId, @Param("offeringId") Long offeringId);
}