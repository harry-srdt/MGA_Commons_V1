package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaSubjectThumbnail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MgaSubjectThumbnailRepository extends JpaRepository<MgaSubjectThumbnail, Long> {
    List<MgaSubjectThumbnail> findByMgaSubject_MgaSubjectId(Long subjectId);

    List<MgaSubjectThumbnail> findByMgaSubject_MgaSubjectIdOrderByCreatedAtAsc(Long mgaSubjectId);
}