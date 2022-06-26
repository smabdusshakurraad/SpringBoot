package com.example.SpringBootTutorial.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "features")
public class FeatureEndPoint {

    // Map to store Feature name and details 
    private final Map<String, Feature> featureMap =
            new ConcurrentHashMap<>();

    // For adding Endpoint's data
    public FeatureEndPoint() {
        featureMap.put("Department", new Feature(true));
        featureMap.put("Employee", new Feature(false));
        featureMap.put("Authentication", new Feature(false));
    }

    @ReadOperation // For reading the data, there is also a @WriteOperation to save the data
    public Map<String, Feature> features(){
        return featureMap;// This endpoints returns features
    }

    @ReadOperation
    public  Feature feature(@Selector String featureName){
        return featureMap.get(featureName); // Return details based on feature name
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Feature {
        // Static class for features
        private boolean isEnabled;

    }


}
