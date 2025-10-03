package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.CoreContentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoreContentTypeRepository extends JpaRepository<CoreContentType, Long> {
    Optional<CoreContentType> findByCode(String code);
}