package com.example.drools.RuleEngine.Config;

import com.example.drools.RuleEngine.RuleBuilder;
import com.example.drools.RuleEngine.Service.DroolsService;
import com.example.drools.RuleEngine.Service.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@DependsOn("DroolsConfig")
public class DBRulesLoader {

    @Autowired
    private DroolsService droolsService;

    @Autowired
    private OffersService offersService;

    @Autowired
    private RuleBuilder ruleBuilder;

    @PostConstruct
    public void init () {
        long start = System.currentTimeMillis();

        offersService
                .findAllOffers()
                .forEach(offer -> droolsService
                        .addRule(ruleBuilder.buildRules(offer), offer.getId()));

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println("Time To Load Rules : "+ timeElapsed);
    }
}
