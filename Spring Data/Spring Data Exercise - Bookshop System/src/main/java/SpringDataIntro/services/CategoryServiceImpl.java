package SpringDataIntro.services;

import com.example.SpringDataIntro.models.Category;
import com.example.SpringDataIntro.repositories.CategoryRepository;
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
