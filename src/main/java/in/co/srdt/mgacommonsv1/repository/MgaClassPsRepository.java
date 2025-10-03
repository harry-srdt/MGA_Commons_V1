package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaClassPs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MgaClassPsRepository extends JpaRepository<MgaClassPs, Long> {
    List<MgaClassPs> findByMgaProgram_MgaProgramIdAndMgaTerm_MgaTermId(Long mgaProgramId, Long mgaTermId);
}