package SpringDataIntro.services;

import SpringDataIntro.models.Category;
import SpringDataIntro.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        List<Category> all = categoryRepository.findAll();

        Collections.shuffle(all);

        return new HashSet<>(all.subList(0, Math.min(2, all.size())));
    }
}
