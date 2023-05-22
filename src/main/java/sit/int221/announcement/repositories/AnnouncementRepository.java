package sit.int221.announcement.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sit.int221.announcement.models.Announcement;
import sit.int221.announcement.utils.Display;

import java.time.ZonedDateTime;

public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {

    @Query("SELECT a FROM Announcement a WHERE a.announcementDisplay = :display AND" +
            "(:category = 0 OR a.category.categoryId = :category) AND" +
            "((a.publishDate IS NULL OR :current >= a.publishDate)) AND" +
            "((a.closeDate IS NULL OR :current < a.closeDate))"
    )
    Page<Announcement> findActive(@Param("display") Display display,
                                  @Param("category") int categoryId,
                                  @Param("current") ZonedDateTime current,
                                  Pageable pageable);

    @Query("SELECT a FROM Announcement a WHERE a.announcementDisplay = :display AND" +
            "(:category = 0 OR a.category.categoryId = :category) AND" +
            "(:current >= a.closeDate)"
    )
    Page<Announcement> findClose(@Param("display") Display display,
                                 @Param("category") int categoryId,
                                 @Param("current") ZonedDateTime current,
                                 Pageable pageable);

}
