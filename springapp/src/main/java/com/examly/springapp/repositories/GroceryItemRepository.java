package com.examly.springapp.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entities.GroceryItem;

import jakarta.transaction.Transactional;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem,Long>{

    Page<GroceryItem> findAll(Pageable pageable);

    @Query("SELECT g FROM GroceryItem g ORDER BY g.name ASC")
    List<GroceryItem> findAllByOrderByNameAsc();


    @Query("SELECT g FROM GroceryItem g WHERE g.price < ?1")
    List<GroceryItem> findItemsBelowPrice(double price);

    
    @Query("SELECT g FROM GroceryItem g WHERE g.quantity >= ?1")
    List<GroceryItem> findItemsWithMinQuantity(int minQuantity);


    @Transactional
    @Modifying
    @Query("UPDATE GroceryItem g SET g.price = ?2 WHERE g.id = ?1")
    void updateItemPrice(int itemId, double newPrice);


    List<GroceryItem> findAll(Sort sort);
}