package com.group20.backend.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class FirebaseConfiguration {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        Resource firebaseConfigFile = new ClassPathResource("web-group-20-firebase-adminsdk-vkc1b-5890d94957.json");
        return FirebaseApp.initializeApp(
                new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(firebaseConfigFile.getInputStream()))
                        .build()
        );
    }
}
