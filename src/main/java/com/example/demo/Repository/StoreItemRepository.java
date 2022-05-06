package com.example.demo.Repository;

import com.example.demo.Model.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StoreItemRepository extends JpaRepository<StoreItem, Integer> {
}
