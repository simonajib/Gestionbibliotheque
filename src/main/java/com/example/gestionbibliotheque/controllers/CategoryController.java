package com.example.gestionbibliotheque.controllers;

import com.example.gestionbibliotheque.Repository.CategoryRepository;
import com.example.gestionbibliotheque.entities.Author;
import com.example.gestionbibliotheque.entities.Book;
import com.example.gestionbibliotheque.entities.Category;
import com.example.gestionbibliotheque.entities.User;
import com.example.gestionbibliotheque.service.CategoryService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/addCategorys")
    public String showAddCategoryForm(Model model) {

            model.addAttribute("category", new Category());
            return "/Librarian/AddCategory";

    }


    @PostMapping("/addCategory")
    public String addCategory(@RequestParam("category") String categoryName) {
        Category category = new Category();
        category.setCategory(categoryName);
        categoryRepository.save(category);

        // Redirect to a success page or to another appropriate page
        return "redirect:/Category";
    }
    @GetMapping(value="/Categorys")
    public String listcategory(
                               Model model) {

            List<Category> categories = categoryService.getAllCategories(); // Assuming you have a service method to get all books
            model.addAttribute("categories", categories);
            return "/Librarian/CategoryList";

    }
    //supprimer un auteur par id
    @GetMapping("/deleteCategory/{id}")
    public String deleteAuthor(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        categoryService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Author deleted successfully!");
        return "redirect:/Category";
    }
}
