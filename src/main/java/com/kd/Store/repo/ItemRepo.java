package com.kd.Store.repo;

import com.kd.Store.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item,Integer> {
    Page<Item> findAllByItemAvailable(boolean itemAvailability, Pageable pageable);
}
