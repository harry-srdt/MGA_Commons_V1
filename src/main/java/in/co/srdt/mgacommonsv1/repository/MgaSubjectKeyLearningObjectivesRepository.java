package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.dto.studentApis.raw.SubjectKeyLearningObjectiveRow;
import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaSubjectKeyLearningObjectives;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MgaSubjectKeyLearningObjectivesRepository extends JpaRepository<MgaSubjectKeyLearningObjectives, Long> {
    @Query(value = """
            select
                k.display_order        as displayOrder,
                k.key_learning_objective as keyLearningObjective
            from mga_subject_key_learning_objectives k
            join mga_subject s on s.mga_subject_id = k.mga_subject_id
            where s.code = :code
            and coalesce(k.active_status,'A') = 'A'
            order by coalesce(k.display_order, 2147483647), k.mga_subject_key_learning_objectives_id
    """, nativeQuery = true)
    List<SubjectKeyLearningObjectiveRow> getSubjectKeyLearningObjectivesBySubjectCode(@Param("code") String subjectCode);

    List<MgaSubjectKeyLearningObjectives> findByMgaSubject_MgaSubjectId(Long subjectId);

    List<MgaSubjectKeyLearningObjectives> findByMgaSubject_MgaSubjectIdOrderByDisplayOrderAsc(Long mgaSubjectId);
}
