package com.project.RK;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiConfig {

        private static final Properties properties = new Properties();

        static {
            try (InputStream inputStream = ApiConfig.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties")) {
                if (inputStream != null) {
                    properties.load(inputStream);
                } else {
                    throw new IOException("Unable to find config.properties");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static String getBaseUri() {
            return properties.getProperty("base_uri");
        }

        public static String getToken() {
            return properties.getProperty("api_token");
        }

        public static String getBasePath() {
            return properties.getProperty("base_path");
        }

        public static String getActivityCommonBasePath() {
            return properties.getProperty("activity_common_base_path");
        }
    public static String getAuthorsCommonBasePath() {
        return properties.getProperty("authors_common_base_path");
    }

        public static String getUserPath() {
            return properties.getProperty("user_path");
        }

    }
