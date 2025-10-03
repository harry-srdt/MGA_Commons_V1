package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaUserManagement.MgaAuthUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MgaAuthUserDetailsRepository extends JpaRepository<MgaAuthUserDetails, String> {
    Optional<MgaAuthUserDetails> findByUsername(String username);
}