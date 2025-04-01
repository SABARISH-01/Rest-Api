package com.examly.springapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entities.User;
import com.examly.springapp.services.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;






@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        
        return userService.createUser(user);
    }

    @GetMapping("/get")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/get/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    @PutMapping("update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User Deleted");
    }

    @GetMapping("/paginated")
    public  ResponseEntity<Page<User>> getUserPaginated(
        @RequestParam(defaultValue = "0") int pages,
        @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(userService.getUsersPaginated(pages, size));
    }

    @GetMapping("/sorted")
    public  ResponseEntity<Page<User>> getUserPaginatedSort(
        @RequestParam(defaultValue = "0") int pages,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "Id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir) {
        return ResponseEntity.ok(userService.getUsersPaginatedSort(pages, size,sortBy,sortDir));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String name) {
    return ResponseEntity.ok(userService.searchUsersByName(name));
    }

    @DeleteMapping("/deletebyname")
    public ResponseEntity<String> deleteUserByName(@RequestParam String name) {
        int deletedCount = userService.deleteUserByName(name);
        if (deletedCount > 0) {
            return ResponseEntity.ok("User(s) with name '" + name + "' deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}