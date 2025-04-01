package com.examly.springapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.entities.MealPlan;
import com.examly.springapp.repositories.MealPlanRepository;

@Service
public class MealPlanService {

    @Autowired
    MealPlanRepository mealPlanRepository;
    
    public MealPlan createUser(MealPlan mealPlan){
        return mealPlanRepository.save(mealPlan);
    }

     public List<MealPlan> getAllMealPlans() {
        return mealPlanRepository.findAll();
    }

    public Optional<MealPlan> getMealPlanById(Long id) {
        return mealPlanRepository.findById(id);
    }

    public List<MealPlan> getMealPlansByUserId(Long userId) {
        return mealPlanRepository.findByUserId(userId);
    }

   
    public List<MealPlan> getSortedMealPlans(String field, String order) {
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(field).descending() : Sort.by(field).ascending();
        return mealPlanRepository.findAll(sort);
    }


    public Page<MealPlan> getMealPlansWithPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return mealPlanRepository.findAll(pageable);
    }

    
    public Page<MealPlan> getMealPlansWithPaginationAndSorting(int pageNumber, int pageSize, String field, String order) {
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(field).descending() : Sort.by(field).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return mealPlanRepository.findAll(pageable);
    }

    public MealPlan updateMealPlan(Long id, MealPlan mealPlan) {
        return mealPlanRepository.findById(id)
                .map(existingPlan -> {
                    existingPlan.setTitle(mealPlan.getTitle());
                    existingPlan.setStartDate(mealPlan.getStartDate());
                    existingPlan.setEndDate(mealPlan.getEndDate());
                    existingPlan.setUser(mealPlan.getUser());  // Fixed user assignment
                    return mealPlanRepository.save(existingPlan);
                })
                .orElse(null);
    }
    

    public void deleteMealPlan(Long id) {
        mealPlanRepository.deleteById(id);
    }
}