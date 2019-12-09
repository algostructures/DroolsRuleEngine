package com.example.drools.RuleEngine.Config;

import com.example.drools.RuleEngine.Service.DroolsSharder;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;

@Component("DroolsConfig")
public class DroolsStartUp {

    private ArrayList<KnowledgeBuilder> knowledgeBuilders = new ArrayList<>();
    private ArrayList<KieSession> kieSessions = new ArrayList<>();

    @PostConstruct
    public void buildKnowledgeShards(){
        for(int i = 0; i < DroolsSharder.TOTAL_SHARDS; i++) {
            knowledgeBuilders.add(KnowledgeBuilderFactory.newKnowledgeBuilder());
        }
    }

    public ArrayList<KieSession> getKieSessions() throws IOException {
        for(int i = 0; i < DroolsSharder.TOTAL_SHARDS; i++) {
            InternalKnowledgeBase kBase = getKnowledgeBase();
            kBase.addPackages(knowledgeBuilders.get(i).getKnowledgePackages());
            StatefulKnowledgeSession kSession = (StatefulKnowledgeSession) kBase.newKieSession();
            kieSessions.add(kSession);
        }
        return kieSessions;
    }

    private InternalKnowledgeBase getKnowledgeBase()  {
        return KnowledgeBaseFactory.newKnowledgeBase();
    }

    public ArrayList<KnowledgeBuilder> getKnowledgeBuilders() {
        return knowledgeBuilders;
    }
}
