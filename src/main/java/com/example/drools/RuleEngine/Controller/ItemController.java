package com.example.drools.RuleEngine.Controller;

import com.example.drools.RuleEngine.Model.Item;
import com.example.drools.RuleEngine.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/order")
    public Item orderNow(@RequestBody Item item) {
        return itemService.orderNow(item);
    }


    @PostMapping("/addItem")
    public Item addItem(@RequestBody Item item) {
        return itemService.addNewItem(item);
    }
}
