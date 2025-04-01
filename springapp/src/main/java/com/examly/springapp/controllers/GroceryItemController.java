package com.examly.springapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entities.GroceryItem;
import com.examly.springapp.services.GroceryItemService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/Grocery")
public class GroceryItemController {

    @Autowired
    GroceryItemService groceryItemService;

    @PostMapping("/create")
    public GroceryItem creaGroceryItem(@RequestBody GroceryItem entity) {
        
        return groceryItemService.creaGroceryItem(entity);
    }
    
    @GetMapping("/sortBy/{field}/{direction}")
    public List<GroceryItem> getSortedItems(@PathVariable String field, @PathVariable String direction) {
        return groceryItemService.getSortedItems(field, direction);
    }

   
    @GetMapping("/paginated")
    public List<GroceryItem> getPaginatedItems(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return groceryItemService.getPaginatedItems(pageNumber, pageSize);
    }

    @GetMapping("/paginated/sorted")
    public List<GroceryItem> getPaginatedSortedItems(@RequestParam int pageNumber, @RequestParam int pageSize,
                                                     @RequestParam String field, @RequestParam String direction) {
        return groceryItemService.getPaginatedSortedItems(pageNumber, pageSize, field, direction);
    }

   
    @GetMapping("/below-price/{price}")
    public List<GroceryItem> getItemsBelowPrice(@PathVariable double price) {
        return groceryItemService.getItemsBelowPrice(price);
    }

   
    @PutMapping("/update-price/{id}/{price}")
    public void updateItemPrice(@PathVariable int id, @PathVariable double price) {
        groceryItemService.updateItemPrice(id, price);
    }

    @DeleteMapping("/delete-expired/{expiryDate}")
    public void deleteExpiredItems(@PathVariable String expiryDate) {
        groceryItemService.deleteExpiredItems(expiryDate);
    }
    
}