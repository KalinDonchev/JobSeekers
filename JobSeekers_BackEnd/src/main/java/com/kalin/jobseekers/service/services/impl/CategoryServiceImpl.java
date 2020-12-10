package com.kalin.jobseekers.service.services.impl;

import com.kalin.jobseekers.data.models.Category;
import com.kalin.jobseekers.data.repositories.CategoryRepository;
import com.kalin.jobseekers.service.models.CategoryServiceModel;
import com.kalin.jobseekers.service.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final List<Category> categories = List.of(
            new Category("Design"),
            new Category("Marketing"),
            new Category("Writing"),
            new Category("Video"),
            new Category("Music"),
            new Category("Programming"),
            new Category("Lifestyle"));


    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<CategoryServiceModel> getAll() {
        if (this.categoryRepository.count() == 0){
            seedCategories();
        }
        return this.categoryRepository.findAll().stream()
                .map(c -> this.modelMapper.map(c,CategoryServiceModel.class))
                .collect(Collectors.toList());
    }

    private void seedCategories() {
        this.categoryRepository.saveAll(categories);
    }
}
