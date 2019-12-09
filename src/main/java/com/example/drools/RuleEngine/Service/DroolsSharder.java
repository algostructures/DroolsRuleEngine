package com.example.drools.RuleEngine.Service;

import com.example.drools.RuleEngine.Config.DroolsStartUp;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class DroolsSharder {

    @Autowired
    private Environment environment;

    @Autowired
    private DroolsStartUp droolsConfig;

    public final static Long TOTAL_SHARDS = 1L;

    KnowledgeBuilder getShard(Long ruleId) {
        return droolsConfig.getKnowledgeBuilders().get((int) (ruleId % TOTAL_SHARDS));
    }

    ArrayList<KieSession> getKieSessions() {
        try {
            return droolsConfig.getKieSessions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
