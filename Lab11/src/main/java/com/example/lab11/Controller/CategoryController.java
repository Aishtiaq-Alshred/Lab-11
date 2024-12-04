package com.example.lab11.Controller;

import com.example.lab11.Model.Category;
import com.example.lab11.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/Category")
//@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get")
    public ResponseEntity getCategory(){

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategory());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body("category added");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id , @Valid @RequestBody Category category, Errors errors ){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        categoryService.updateCategory(id,category);
        return ResponseEntity.status(HttpStatus.OK).body("category update");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){

        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("category delete");
    }
}
