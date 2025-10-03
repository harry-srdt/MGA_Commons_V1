package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaUserAvatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MgaUserAvatarRepository extends JpaRepository<MgaUserAvatar, Long> {
    Optional<MgaUserAvatar> findFirstByAuthUser_UsernameAndIsPrimaryTrueAndActiveStatus(String username, String activeStatus);
    List<MgaUserAvatar> findByAuthUser_UsernameOrderByCreatedAtDesc(String username);
}