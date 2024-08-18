package config;

import java.io.*;
import java.util.Properties;

public class DBConfigurator {

    public Properties getDBConfig() throws IOException {
        Properties properties = new Properties();
        InputStream fileInputStream = new FileInputStream(
                System.getProperty("user.dir") + "src/resources/db.properties"
        );
        properties.load(fileInputStream);

        return properties;
    }

}
