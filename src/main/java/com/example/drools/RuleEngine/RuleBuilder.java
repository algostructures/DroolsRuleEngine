package com.example.drools.RuleEngine;


import com.example.drools.RuleEngine.Model.Offers;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;

@Service
public class RuleBuilder {

    @Autowired
    private VelocityEngine velocityEngine;

    public String buildRules (Offers offer){
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("id", offer.getId());
        velocityContext.put("ruleName", offer.getRuleName());
        velocityContext.put("price", offer.getPrice());
        velocityContext.put("discountAmount", offer.getDiscountAmount());
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate("templates/rules.vm", "UTF-8", velocityContext, stringWriter);
        return stringWriter.toString();
    }

}
