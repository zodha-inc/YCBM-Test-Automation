package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

    protected static Properties lambdaProperties;
    protected static Properties localProperties;

    public static void initializeLambadProperties() {
        lambdaProperties = new Properties();
        File lambdaConfigFile = new File("src\\test\\testConfigs\\lambdaConfig.properties");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(lambdaConfigFile);
            lambdaProperties.load(fis);
        } catch (FileNotFoundException e) {
            System.out.println("File path for LambdaConfig is not correct.");
        } catch (IOException e) {
            System.out.println("Config file load is failed.");
        }
    }

    public static String getLambdaConfigProperty(String key) {
        return lambdaProperties.getProperty(key);
    }

    public static void initializeLocalProperties() {
        localProperties = new Properties();
        File localConfigFile = new File("src\\test\\testConfigs\\localConfig.properties");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(localConfigFile);
            localProperties.load(fis);
        } catch (FileNotFoundException e) {
            System.out.println("File path for LambdaConfig is not correct.");
        } catch (IOException e) {
            System.out.println("Config file load is failed.");
        }
    }

    public static String getLocalConfigProperty(String key) {
        return localProperties.getProperty(key);
    }

}
