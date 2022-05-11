package com.example.demo.controller;
import com.example.demo.Model.StoreItem;
import com.example.demo.Repository.StoreItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class StoreController {

    @Autowired

    private StoreItemRepository storeItemRepository;

    @GetMapping("/getStoreItem")
    public List<StoreItem> getAllStoreItemData() {
        return storeItemRepository.findAll();
    }

    @GetMapping("/checkitem")
    public String CheckItem() {
        return "checking cart dockarization";
    }

    @PostMapping("/addstoreitem")
    public ResponseEntity<?> addStoreItemData(@RequestBody StoreItem storeItem) {
        try {
            storeItemRepository.save(storeItem);
            return new ResponseEntity<>(storeItem, HttpStatus.OK);//
        }catch (Exception e){
            System.out.println("si " + e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/deleteStoreItem/{id}")
    public ResponseEntity<?> deleteStoreItem(@PathVariable("id") int id){
        storeItemRepository.deleteById(id);
        return new ResponseEntity<>("delete successful", HttpStatus.OK);
    }

//    @DeleteMapping("/deleteCartItem/{id}")
//    public ResponseEntity<?> deleteCartItem(@PathVariable("id") int id){
//        return new ResponseEntity<>("delete successful", HttpStatus.OK);
//    }



    





}
