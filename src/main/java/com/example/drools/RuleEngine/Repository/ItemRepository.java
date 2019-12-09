package com.example.drools.RuleEngine.Repository;

import com.example.drools.RuleEngine.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Override
    List<Item> findAll();

    Optional<Item> findById(Long id);
}
