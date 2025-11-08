package com.mhgjoker.education.system.config;

import com.mhgjoker.education.system.config.properties.MinioProperties;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MinioConfig {
    private final MinioProperties properties;

    @Bean
    public MinioClient minioClient(){
        return MinioClient
                .builder()
                .endpoint(properties.getMinioUrl())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();
    }
}
