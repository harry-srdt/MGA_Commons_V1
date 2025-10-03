package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaForum.MgaForumPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MgaForumPostRepository extends JpaRepository<MgaForumPost, Long> {
    @Query("""
        select p from MgaForumPost p
        where p.mgaForumTopic.mgaForumTopicId = :topicId
          and coalesce(p.activeStatus, 'A') = 'A'
        order by p.createdAt asc
        """)
    Page<MgaForumPost> findByTopic(@Param("topicId") Long topicId, Pageable pageable);
}