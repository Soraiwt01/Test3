package sit.int221.announcement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import sit.int221.announcement.exceptions.list.ItemNotFoundException;
import sit.int221.announcement.models.Category;
import sit.int221.announcement.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category getCategoryById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException("categoryId","does not exists"));
    }
}
