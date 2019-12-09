package com.example.drools.RuleEngine.Service;

import com.example.drools.RuleEngine.Model.Item;
import com.example.drools.RuleEngine.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private DroolsService droolsService;

    @Transactional
    public Item addNewItem (Item item) {
        return itemRepository.save(item);
    }

    Optional<Item> findItemById(Long id){
        return itemRepository.findById(id);
    }

    List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public Item orderNow(Item item) {
        return droolsService.runQuery(item);
    }
}
