package com.example.drools.RuleEngine.Service;

import com.example.drools.RuleEngine.Model.Offers;
import com.example.drools.RuleEngine.Repository.OfferRepository;
import com.example.drools.RuleEngine.RuleBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OffersService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private DroolsService droolsService;

    @Autowired
    private RuleBuilder ruleBuilder;

    public Optional<Offers> findOfferById(Long id){
        return offerRepository.findById(id);
    }

    public List<Offers> findAllOffers() {
        return offerRepository.findAll();
    }

    @Transactional
    public Offers submitOffer(Offers offers){
        Offers offer = offerRepository.save(offers);
        if(offer != null) {
            droolsService.addRule(ruleBuilder.buildRules(offer), offer.getId());
        }
        return offer;
    }
}
