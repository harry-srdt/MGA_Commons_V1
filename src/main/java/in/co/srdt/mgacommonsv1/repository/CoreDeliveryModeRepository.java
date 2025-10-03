package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.CoreDeliveryMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CoreDeliveryModeRepository extends JpaRepository<CoreDeliveryMode, Long> {
    Optional<CoreDeliveryMode> findByCode(String code);

    @Query("select dm from CoreDeliveryMode dm where coalesce(dm.activeStatus, 'A') = 'A'")
    List<CoreDeliveryMode> findAllActive();
}