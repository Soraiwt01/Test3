package sit.int221.announcement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.announcement.models.Announcement;
import sit.int221.announcement.models.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> { }
