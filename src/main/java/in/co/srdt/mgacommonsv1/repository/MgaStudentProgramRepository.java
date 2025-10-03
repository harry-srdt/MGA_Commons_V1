package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaStudentProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MgaStudentProgramRepository extends JpaRepository<MgaStudentProgram, MgaStudentProgram.MgaStudentProgramId> {
    @Query("""
        select sp
        from MgaStudentProgram sp
        join sp.mgaStudent s
        where s.rollNumber = :roll
        order by
            case when sp.activeStatus = 'A' then 0 else 1 end,
            sp.admitDate desc
    """)
    List<MgaStudentProgram> findOrderedByRoll(@Param("roll") String roll);
}