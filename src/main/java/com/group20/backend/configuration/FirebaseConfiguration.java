package com.group20.backend.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseConfiguration {

    @PostConstruct
    public FirebaseApp firebaseApp() throws IOException {
        File file = ResourceUtils.getFile("classpath:web-group-20-firebase-adminsdk-vkc1b-5890d94957.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(file)))
                .setServiceAccountId("firebase-adminsdk-vkc1b@web-group-20.iam.gserviceaccount.com")
                .build();
        return FirebaseApp.initializeApp(options);
    }
}
