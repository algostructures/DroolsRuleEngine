package com.example.drools.RuleEngine.Service;

import com.example.drools.RuleEngine.Model.Item;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class DroolsService {

    @Autowired
    private DroolsSharder droolsSharder;

    public void addRule(String ruleContent, Long id) {
        droolsSharder
                .getShard(id)
                .add(ResourceFactory
                        .newByteArrayResource(ruleContent
                                .getBytes(StandardCharsets.UTF_8)), ResourceType.DRL);

        KnowledgeBuilderErrors errors = droolsSharder.getShard(id).getErrors();

        for (KnowledgeBuilderError error : errors) {
            System.out.println(error);
        }
    }

    public Item runQuery(Item item) {
        for(KieSession kieSession : droolsSharder.getKieSessions()) {
            kieSession.insert(item);
            if(kieSession.fireAllRules() > 0){
                kieSession.dispose();
                break;
            } else {
                kieSession.dispose();
            }
        }
        droolsSharder.getKieSessions().clear();
        return item;
    }
}
