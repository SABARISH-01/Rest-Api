package com.examly.springapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entities.MealPlan;
import com.examly.springapp.services.MealPlanService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/Mealplan")
public class MealPlanController {

    @Autowired
    MealPlanService mealPlanService;
    
    @PostMapping("/create")
    public MealPlan postMealPlan(@RequestBody MealPlan mealPlan) {
        //TODO: process POST request
        
        return mealPlanService.createUser(mealPlan);
    }
    
     @GetMapping
    public ResponseEntity<List<MealPlan>> getAllMealPlans() {
        return ResponseEntity.ok(mealPlanService.getAllMealPlans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealPlan> getMealPlanById(@PathVariable Long id) {
        Optional<MealPlan> mealPlan = mealPlanService.getMealPlanById(id);
        return mealPlan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MealPlan>> getMealPlansByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(mealPlanService.getMealPlansByUserId(userId));
    }

    // ✅ Sorting by a specific field (ASC or DESC)
    @GetMapping("/sort/{field}")
    public ResponseEntity<List<MealPlan>> getSortedMealPlans(
            @PathVariable String field,
            @RequestParam(defaultValue = "asc") String order) {
        return ResponseEntity.ok(mealPlanService.getSortedMealPlans(field, order));
    }

    // ✅ Pagination support
    @GetMapping("/page/{pageNumber}/size/{pageSize}")
    public ResponseEntity<Page<MealPlan>> getMealPlansWithPagination(
            @PathVariable int pageNumber,
            @PathVariable int pageSize) {
        return ResponseEntity.ok(mealPlanService.getMealPlansWithPagination(pageNumber, pageSize));
    }

    // ✅ Pagination with Sorting
    @GetMapping("/page/{pageNumber}/size/{pageSize}/sort/{field}")
    public ResponseEntity<Page<MealPlan>> getMealPlansWithPaginationAndSorting(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String field,
            @RequestParam(defaultValue = "asc") String order) {
        return ResponseEntity.ok(mealPlanService.getMealPlansWithPaginationAndSorting(pageNumber, pageSize, field, order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealPlan> updateMealPlan(@PathVariable Long id, @RequestBody MealPlan mealPlan) {
        MealPlan updatedMealPlan = mealPlanService.updateMealPlan(id, mealPlan);
        return updatedMealPlan != null ? ResponseEntity.ok(updatedMealPlan) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMealPlan(@PathVariable Long id) {
        mealPlanService.deleteMealPlan(id);
        return ResponseEntity.ok("Meal plan deleted successfully");
    }
}