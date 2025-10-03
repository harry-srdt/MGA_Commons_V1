package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.dto.academicStructure.FacultyBasicRow;
import in.co.srdt.mgacommonsv1.dto.studentApis.raw.FacultyRatingRow;
import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaFaculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MgaFacultyRepository extends JpaRepository<MgaFaculty, Long> {
    @Query(value = """
        select avg_rating, ratings_count
        from v_faculty_rating_summary
        where mga_faculty_id = :fid
        """, nativeQuery = true)
    FacultyRatingRow findRatings(@Param("fid") long facultyId);

    @Query("""
        select f.mgaFacultyId as id,
               f.name         as name,
               f.email        as email,
               f.phone        as phone,
               f.specialization as specialization,
               f.about        as about
        from MgaFaculty f
        where coalesce(f.activeStatus, 'A') = 'A'
        order by f.name asc
    """)
    List<FacultyBasicRow> findAllActiveBasics();

    // Search by name/email/phone
    @Query("""
        select f.mgaFacultyId as id,
               f.name         as name,
               f.email        as email,
               f.phone        as phone,
               f.specialization as specialization,
               f.about        as about
        from MgaFaculty f
        where coalesce(f.activeStatus, 'A') = 'A'
          and (
               upper(f.name)  like concat('%', upper(:q), '%')
            or upper(f.email) like concat('%', upper(:q), '%')
            or upper(f.phone) like concat('%', upper(:q), '%')
          )
        order by f.name asc
    """)
    List<FacultyBasicRow> searchActiveBasics(@org.springframework.data.repository.query.Param("q") String q);


    @Query(value = "SELECT f.mga_faculty_id FROM mga_faculty f WHERE f.erp_id = :u AND COALESCE(f.active_status,'A')='A' LIMIT 1", nativeQuery = true)
    Optional<Long> findIdByUsername(@Param("u") String username);

}