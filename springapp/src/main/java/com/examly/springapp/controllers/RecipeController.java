package com.examly.springapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entities.Recipe;
import com.examly.springapp.services.RecipeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/recipe")
public class RecipeController{

    @Autowired
    RecipeService recipeService;

    @PostMapping("/create")
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeService.createRecipe(recipe);
    }
    
    @GetMapping("/get")
    public List<Recipe> getRecipes() {
        return recipeService.getRecipes();
    }

    @GetMapping("/get/{id}")
    public Optional<Recipe> getRecipebyId(@PathVariable Long id) {
        return recipeService.getRecipebyId(id);
    }
    
    @PutMapping("update/{id}")
    public Recipe updateRecipe(@PathVariable Long id, @RequestBody Recipe updatedRecipe) {
        
        return recipeService.updateRecipe(id, updatedRecipe);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id){
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok("Recipe Deleted");
    }
}