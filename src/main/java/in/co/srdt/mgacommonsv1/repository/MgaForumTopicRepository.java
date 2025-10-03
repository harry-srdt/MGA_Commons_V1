package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaForum.MgaForumTopic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MgaForumTopicRepository extends JpaRepository<MgaForumTopic, Long> {

    // List topics for an offering with search + sort by last activity
    @Query(value = """
        SELECT t.* FROM mga_forum_topic t
        WHERE t.mga_subject_offering_id = :offeringId
          AND COALESCE(t.active_status, 'A') = 'A'
          AND (:q IS NULL OR t.title LIKE CONCAT('%', :q, '%'))
        ORDER BY t.last_activity_at DESC
        """, nativeQuery = true)
    Page<MgaForumTopic> findTopics(@Param("offeringId") Long offeringId,
                                   @Param("q") String query,
                                   Pageable pageable);

    long countByMgaSubjectOffering_MgaSubjectOfferingIdAndActiveStatus(Long offeringId, String activeStatus);

    @Modifying
    @Query("""
       UPDATE MgaForumTopic t
          SET t.replyCount = COALESCE(t.replyCount, 0) + 1,
              t.lastActivityAt = CURRENT_TIMESTAMP
        WHERE t.mgaForumTopicId = :topicId
    """)
    int bumpStats(@Param("topicId") Long topicId);
}