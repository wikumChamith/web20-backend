package com.group20.backend.repository;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.group20.backend.model.Product;
import com.group20.backend.util.Constants;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
public class FirebaseProductRepository implements ProductRepository {

    private final Firestore firestore = FirestoreClient.getFirestore();

    @Override
    public boolean create(Product product) {
        return firestore.collection(Constants.PRODUCT_COLLECTION).add(product).isDone();
    }

    @Override
    public Optional<Product> findById(String id) throws ExecutionException, InterruptedException {
        DocumentSnapshot documentSnapshot = firestore.collection(Constants.PRODUCT_COLLECTION).document(id).get().get();
        if(documentSnapshot.exists()) {
            return Optional.ofNullable(documentSnapshot.toObject(Product.class));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> getAll() throws ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> documentSnapshots =  firestore.collection(Constants.PRODUCT_COLLECTION).get()
                .get().getDocuments();
        List<Product> products = new ArrayList<>();
        for(QueryDocumentSnapshot documentSnapshot : documentSnapshots) {
            products.add(documentSnapshot.toObject(Product.class));
        }
        return products;
    }

    @Override
    public Product update(Product product) {
        firestore.collection(Constants.PRODUCT_COLLECTION).document(product.getId()).set(product, SetOptions.merge());
        return product;
    }

    @Override
    public void deleteById(String id) {
        firestore.collection(Constants.PRODUCT_COLLECTION).document(id).delete();
    }
}
