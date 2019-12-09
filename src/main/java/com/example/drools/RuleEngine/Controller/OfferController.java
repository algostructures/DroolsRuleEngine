package com.example.drools.RuleEngine.Controller;

import com.example.drools.RuleEngine.Model.Offers;
import com.example.drools.RuleEngine.Service.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class OfferController {

    @Autowired
    private OffersService offersService;

    @PostMapping("/offer")
    public void orderNow(@RequestBody Offers offer) {
        offersService.submitOffer(offer);
    }
}
