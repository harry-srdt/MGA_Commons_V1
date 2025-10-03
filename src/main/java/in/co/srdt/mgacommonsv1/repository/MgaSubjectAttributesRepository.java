package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaSubjectAttributes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MgaSubjectAttributesRepository extends JpaRepository<MgaSubjectAttributes, Long> {
    Optional<MgaSubjectAttributes> findByMgaSubject_MgaSubjectId(Long subjectId);

    Optional<MgaSubjectAttributes> findFirstByMgaSubject_MgaSubjectIdOrderByUpdatedAtDesc(Long mgaSubjectId);
}