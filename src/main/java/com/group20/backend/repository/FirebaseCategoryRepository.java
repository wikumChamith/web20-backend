package com.group20.backend.repository;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.group20.backend.model.Category;
import com.group20.backend.util.Constants;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
public class FirebaseCategoryRepository implements CategoryRepository {

    private final Firestore firestore = FirestoreClient.getFirestore();
    @Override
    public boolean create(Category category) {
        return firestore.collection(Constants.CATEGORY_COLLECTION).add(category).isDone();
    }

    @Override
    public Optional<Category> findById(String id) throws ExecutionException, InterruptedException {
        DocumentSnapshot documentSnapshot = firestore.collection(Constants.CATEGORY_COLLECTION).document(id).get().get();
        if(documentSnapshot.exists()) {
            return Optional.ofNullable(documentSnapshot.toObject(Category.class));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Category> getAll() throws ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> documentSnapshots =  firestore.collection(Constants.CATEGORY_COLLECTION).get()
                .get().getDocuments();
        List<Category> category = new ArrayList<>();
        for(QueryDocumentSnapshot documentSnapshot : documentSnapshots) {
            category.add(documentSnapshot.toObject(Category.class));
        }
        return category;
    }

    @Override
    public Category update(Category category) {
        firestore.collection(Constants.CATEGORY_COLLECTION).document(category.getId()).set(category, SetOptions.merge());
        return category;
    }

    @Override
    public void deleteById(String id) {
        firestore.collection(Constants.CATEGORY_COLLECTION).document(id).delete();
    }
}
