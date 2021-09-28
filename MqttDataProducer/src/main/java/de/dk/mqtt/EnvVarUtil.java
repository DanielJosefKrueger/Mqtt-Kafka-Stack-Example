package de.dk.mqtt;

import static org.mockito.Mockito.mock;

public class EnvVarUtil {


    static int getOrDefault(String key, int defaultValue){
        String value = System.getenv(key);
        if( value != null){
            try{
                int parseInt = Integer.parseInt(value);
                System.out.println("Found value " + parseInt + " for Env. Variable " + key);
                return parseInt;
            }catch (final Exception e){
                System.out.println("Exception when trying to convert the value " + value + " to int");
                e.printStackTrace();
                return defaultValue;
            }
        }else{
            System.out.println("Env. Variable " + key + "is not set. Using default value: " + defaultValue);
            return defaultValue;
        }
    }

    static String getOrDefault(String key, String defaultValue){
        String value = System.getenv(key);
        if( value != null){
            System.out.println("Found value " + value + " for Env. Variable " + key);
            return value;
        }else{
            System.out.println("Env. Variable " + key + "is not set. Using default value: " + defaultValue);
            return defaultValue;
        }
    }

}
