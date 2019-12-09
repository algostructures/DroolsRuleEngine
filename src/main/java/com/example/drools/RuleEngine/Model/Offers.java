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
public class Offers {
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    int price;
    String ruleName;
    int discountAmount;
}
