package SpringDataIntro.services;

import SpringDataIntro.models.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();
}
