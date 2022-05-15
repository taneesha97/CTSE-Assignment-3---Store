package com.example.demo.controller;
import com.example.demo.Model.StoreItem;
import com.example.demo.Repository.StoreItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/updatestoreitem/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") int id, @RequestBody StoreItem storeItem){
        Optional<StoreItem> storeItemUpdate = Optional.ofNullable(storeItemRepository.getById(id));
        if(storeItemUpdate.isPresent()){
            StoreItem updateStoreItem = storeItemUpdate.get();
            updateStoreItem.setName(storeItem.getName() != null ? storeItem.getName() : updateStoreItem.getName());
            updateStoreItem.setDescription(storeItem.getDescription() != null ? storeItem.getDescription() : updateStoreItem.getDescription());
            updateStoreItem.setPrice(storeItem.getPrice() != null ? storeItem.getPrice() : updateStoreItem.getPrice());
            updateStoreItem.setStock(storeItem.getStock() != 0 ? storeItem.getStock() : updateStoreItem.getStock());
            updateStoreItem.setImg_url(storeItem.getImg_url() != null ? storeItem.getImg_url() : updateStoreItem.getImg_url());
            StoreItem value = storeItemRepository.save(updateStoreItem);
            //System.out.println("hi " + updateUser);
            return new ResponseEntity<>(value, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Store Items Data Available", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteStoreItem/{id}")
    public ResponseEntity<?> deleteStoreItem(@PathVariable("id") int id){
        storeItemRepository.deleteById(id);
        return new ResponseEntity<>("delete successful", HttpStatus.OK);
    }




    





}
