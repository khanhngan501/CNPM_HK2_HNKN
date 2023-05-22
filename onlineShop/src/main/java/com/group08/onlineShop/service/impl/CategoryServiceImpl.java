package com.group08.onlineShop.service.impl;

import com.group08.onlineShop.dto.CategoryDTO;
import com.group08.onlineShop.exception.AppException;
import com.group08.onlineShop.model.Category;
import com.group08.onlineShop.repository.CategoryRepo;
import com.group08.onlineShop.service.CategoryService;
import com.group08.onlineShop.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Category findById(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        return category.orElse(null);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category save(Category categoryDTO) {
        Long cate_id = Long.valueOf(sequenceGeneratorService.generateSequence(categoryDTO.SEQUENCE_NAME));
        var category = Category.builder()
                .id(cate_id)
                .categoryName(categoryDTO.getCategoryName()).build();
        return categoryRepo.save(category);
    }

    @Override
    public Category updateCategory(CategoryDTO categoryDTO) {
        Category categoryUpdate = findById(categoryDTO.getId());
        if (categoryUpdate != null) {
            categoryUpdate.setCategoryName(categoryDTO.getCategoryName());

            return categoryRepo.save(categoryUpdate);
        } else
            throw new AppException(404, "Comment ID not found");

    }

    @Override
    public Boolean deleteCategory(Long categoryId) {
        Category categoryDelete = findById(categoryId);
        if (categoryDelete != null) {
            categoryRepo.deleteById(categoryDelete.getId());
            return true;
        } else
            return false;
    }
}
