package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    // Bu class'in amaci;
    // Test method'larinda ihtiyac duyulan TEST DATALARI'ni
    // configuration.properties dosyasinda bulup, test method'una getirmek

    static Properties properties;

    static { // static blok herseyden once calisir
        String dosyaYolu = "configuration.properties";
        try {
            FileInputStream fis = new FileInputStream(dosyaYolu);
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("properties dosyasi okunamadi");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }


}
