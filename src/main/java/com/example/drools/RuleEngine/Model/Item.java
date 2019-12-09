package com.example.drools.RuleEngine.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
    private String name;
    private String cardType;
    private int discount;
    private int price;
}
