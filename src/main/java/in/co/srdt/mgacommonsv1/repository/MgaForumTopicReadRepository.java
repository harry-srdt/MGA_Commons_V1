package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaForum.MgaForumTopicRead;
import in.co.srdt.mgacommonsv1.entity.mgaForum.PrincipalType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MgaForumTopicReadRepository extends JpaRepository<MgaForumTopicRead, Long> {
    Optional<MgaForumTopicRead> findByMgaForumTopic_MgaForumTopicIdAndReaderTypeAndReaderId(
            Long topicId, PrincipalType type, Long readerId
    );
}