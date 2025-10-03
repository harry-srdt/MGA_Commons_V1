package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.CoreStorageProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CoreStorageProviderRepository extends JpaRepository<CoreStorageProvider, Long> {
    Optional<CoreStorageProvider> findByCode(String code);

    @Query("""
        select c
        from CoreStorageProvider c
        where coalesce(c.activeStatus, 'A') = 'A'
        order by c.name asc
    """)
    List<CoreStorageProvider> findAllActive();
}