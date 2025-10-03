package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MgaStudentRepository extends JpaRepository<MgaStudent, Long> {
    Optional<MgaStudent> findByRollNumber(String rollNumber);

    @Query(value = """
        SELECT s.mga_student_id 
        FROM mga_student s 
        WHERE s.roll_number = :u 
            AND COALESCE(s.active_status,'A')='A' LIMIT 1
    """, nativeQuery = true)
    Optional<Long> findIdByUsername(@Param("u") String username);
}