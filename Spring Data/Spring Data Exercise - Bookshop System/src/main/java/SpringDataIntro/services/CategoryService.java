package SpringDataIntro.services;

import com.example.SpringDataIntro.models.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();
}
