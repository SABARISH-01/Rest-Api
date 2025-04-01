package com.examly.springapp.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.entities.User;
import com.examly.springapp.repositories.UserRepository;

import jakarta.transaction.Transactional;



@Service
public class UserService{
    @Autowired
    UserRepository userRepo;
    
    public User createUser(User user){
        return userRepo.save(user);
    }

    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepo.findById(id);
    }

    public User updateUser(Long id,User updatedUser){
        return userRepo.findById(id)
        .map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return userRepo.save(user);
        })
        .orElseThrow(() -> new RuntimeException("User not Found"));
    }

    public void deleteUser(Long Id){
        if(userRepo.existsById(Id)){
            userRepo.deleteById(Id);
        }
        else{
            throw new RuntimeException("User not Found with ID "+ Id);
        }
    }

    public Page<User> getUsersPaginated(int pages,int size){
        Pageable pageable = PageRequest.of(pages,size);
        return userRepo.findAll(pageable);
    }

    public Page<User> getUsersPaginatedSort(int pages,int size,String sortId,String sortDir){
        Sort sort=sortDir.equalsIgnoreCase("desc") ? Sort.by(sortId).descending() : Sort.by(sortId).ascending();
        Pageable pageable = PageRequest.of(pages,size,sort);
        return userRepo.findAll(pageable);
    }

    public List<User> searchUsersByName(String name){
        return userRepo.searchUsersByName(name); 
    }

    @Transactional
    public int deleteUserByName(String name) {
        return userRepo.deleteByName(name);
    }
}