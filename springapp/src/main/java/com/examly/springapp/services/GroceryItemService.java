package com.examly.springapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.entities.GroceryItem;
import com.examly.springapp.repositories.GroceryItemRepository;

@Service
public class GroceryItemService {

    @Autowired
    GroceryItemRepository groceryItemRepository;

    public GroceryItem creaGroceryItem(GroceryItem groceryItem){
        return groceryItemRepository.save(groceryItem);
    }
    
     public List<GroceryItem> getSortedItems(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("DESC") ? Sort.by(field).descending() : Sort.by(field).ascending();
        return groceryItemRepository.findAll(sort);
    }

    // Get all grocery items with pagination
    public List<GroceryItem> getPaginatedItems(int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return groceryItemRepository.findAll(page).getContent();
    }

    // Get all grocery items with pagination and sorting
    public List<GroceryItem> getPaginatedSortedItems(int pageNumber, int pageSize, String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("DESC") ? Sort.by(field).descending() : Sort.by(field).ascending();
        Pageable page = PageRequest.of(pageNumber, pageSize, sort);
        return groceryItemRepository.findAll(page).getContent();
    }

    // Get grocery items that are below a specific price
    public List<GroceryItem> getItemsBelowPrice(double price) {
        return groceryItemRepository.findItemsBelowPrice(price);
    }

    // Get grocery items that have a minimum quantity
    public List<GroceryItem> getItemsWithMinQuantity(int quantity) {
        return groceryItemRepository.findItemsWithMinQuantity(quantity);
    }

    // Update the price of a grocery item by its ID
    public void updateItemPrice(int itemId, double newPrice) {
        groceryItemRepository.updateItemPrice(itemId, newPrice);
    }

    
}