package com.ebricks.scriptexecutor.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DesiredCapabilitiesConfig {

    private static DesiredCapabilitiesConfig instance;

    Map<String, Object> details;

    private DesiredCapabilitiesConfig(){
        details = new LinkedHashMap<String, Object>();
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public static DesiredCapabilitiesConfig getInstance() throws IOException {
        if(instance == null)
            instance = new ObjectMapper().readValue(new File("resources/desiredCapabilities.json"), DesiredCapabilitiesConfig.class);
        return instance;
    }
}
