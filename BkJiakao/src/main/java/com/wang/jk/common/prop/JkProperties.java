package com.wang.jk.common.prop;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@ConfigurationProperties("jk")
@Component
@Data
public class JkProperties implements InitializingBean {
    private static JkProperties properties;
    private Web web;
    private Upload upload;
    private Token token;

    public static JkProperties get() {
        return properties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        properties = this;
    }

    @Data
    public static class Token {
        private String issuer;
        private String secret;
        private Duration accessTokenDuration;
        private Duration refreshTokenDuration;
    }

    @Data
    public static class Web {
        private String[] corsOrigins;
    }

    @Data
    public static class Upload {
        private String basePath;
        private String uploadPath;
        private String imagePath;
        private String videoPath;

        public String getImageRelativePath() {
            return uploadPath + imagePath;
        }

        public String getVideoRelativePath() {
            return uploadPath + videoPath;
        }
    }
}
