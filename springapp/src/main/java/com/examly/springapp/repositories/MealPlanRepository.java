package com.examly.springapp.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entities.MealPlan;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan,Long>{

    List<MealPlan> findByUserId(Long userId);

    // ✅ JPQL Query: Find meal plans sorted by a field
    @Query("SELECT m FROM MealPlan m ORDER BY " +
           "CASE WHEN :order = 'asc' THEN m.title END ASC, " +
           "CASE WHEN :order = 'desc' THEN m.title END DESC")
    List<MealPlan> findAllSorted(String order, Sort sort);
}