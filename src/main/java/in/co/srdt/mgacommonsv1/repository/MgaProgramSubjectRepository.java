package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaProgramSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MgaProgramSubjectRepository extends JpaRepository<MgaProgramSubject, MgaProgramSubject.MgaProgramSubjectId> {
    List<MgaProgramSubject> findById_MgaProgramId(Long mgaProgramId);
    List<MgaProgramSubject> findById_MgaSubjectId(Long mgaSubjectId);
}