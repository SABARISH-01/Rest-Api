package com.examly.springapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.entities.Recipe;
import com.examly.springapp.repositories.RecipeRepository;

@Service
public class RecipeService{
    @Autowired
    RecipeRepository recipeRepo;

    public Recipe createRecipe(Recipe recipe){
        return recipeRepo.save(recipe);

    }

    public List<Recipe> getRecipes(){
        return recipeRepo.findAll();
    }

    public Optional<Recipe> getRecipebyId(Long Id){
        return recipeRepo.findById(Id);
    }

    public Recipe updateRecipe(Long id,Recipe updatedRecipe){
        return recipeRepo.findById(id)
        .map(recipe -> {
            recipe.setName(updatedRecipe.getName());
            recipe.setIngredients(updatedRecipe.getIngredients());
            return recipeRepo.save(recipe);
        })
        .orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    public void deleteRecipe(Long id){
  
        if(recipeRepo.existsById(id)){
            recipeRepo.deleteById(id);
        }
        else 
          throw new RuntimeException("Recipe not found");
    }
} 