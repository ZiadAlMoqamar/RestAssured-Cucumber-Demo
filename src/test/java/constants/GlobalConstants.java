package constants;

import utils.PropertiesFile;

public class GlobalConstants {
    public static final String CONFIG_PROPERTIES_FILE = "config.properties";
    public static final String BASE_URI = PropertiesFile.getProperty("baseURL");
    public static final String Activity_Endpoint = PropertiesFile.getProperty("activityEndpoint");
    public static final String RANDOM_ACTIVITY_RESPONSE_SCHEMA = PropertiesFile.getProperty("randomActivityResponseSchema");
}
