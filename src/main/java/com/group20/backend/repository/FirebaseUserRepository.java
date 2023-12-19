package com.group20.backend.repository;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.group20.backend.model.User;
import com.group20.backend.util.Constants;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
public class FirebaseUserRepository implements UserRepository {

    private final Firestore firestore = FirestoreClient.getFirestore();

    @Override
    public boolean create(User user) {
        return firestore.collection(Constants.USER_COLLECTION).add(user).isDone();
    }

    @Override
    public Optional<User> findById(String id) throws ExecutionException, InterruptedException {
        DocumentSnapshot documentSnapshot = firestore.collection(Constants.USER_COLLECTION).document(id).get().get();
        if(documentSnapshot.exists()) {
            return Optional.ofNullable(documentSnapshot.toObject(User.class));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<User> getAll() throws ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> documentSnapshots =  firestore.collection(Constants.USER_COLLECTION).get()
                .get().getDocuments();
        List<User> users = new ArrayList<>();
        for(QueryDocumentSnapshot documentSnapshot : documentSnapshots) {
            users.add(documentSnapshot.toObject(User.class));
        }
        return users;
    }

    @Override
    public User update(User user) {
        firestore.collection(Constants.USER_COLLECTION).document(user.getUid()).set(user, SetOptions.merge());
        return user;
    }

    @Override
    public void deleteById(String id) {
        firestore.collection(Constants.USER_COLLECTION).document(id).delete();
    }
}
