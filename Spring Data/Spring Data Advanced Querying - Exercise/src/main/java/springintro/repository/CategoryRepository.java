package springintro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springintro.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
