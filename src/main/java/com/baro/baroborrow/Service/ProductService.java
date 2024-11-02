package com.baro.baroborrow.Service;

import com.baro.baroborrow.DTO.ProductReturnDTO;
import com.baro.baroborrow.Domain.Product;
import com.baro.baroborrow.Domain.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    public void addProduct(Product product) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection("Product");
        collectionRef.document().set(product);
    }

    public List<ProductReturnDTO> getProduct(String user_id) throws Exception {
        UserService userService = new UserService();
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection("Product");

        Query query1 = collectionRef.whereEqualTo("user_id", user_id);

        List<ProductReturnDTO> products = new ArrayList<>();
        ApiFuture<QuerySnapshot> future1 = query1.get();

        QuerySnapshot querySnapshot1 = future1.get();

        for (DocumentSnapshot document : querySnapshot1.getDocuments()) {
            Product product = document.toObject(Product.class);
            User user = userService.getUser(product.getUser_id());
            User user2 = userService.getUser(product.getUser_id2());
            ProductReturnDTO productReturnDTO = new ProductReturnDTO(product.getProduct_id(), user.getUsername(), user2.getUsername(), product.getCategory(), product.getBorrow_date(), product.getReturn_date());
            products.add(productReturnDTO);
        }
        return products;
    }

    public List<ProductReturnDTO> getProduct2(String user_id) throws Exception {
        UserService userService = new UserService();

        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection("Product");

        Query query2 = collectionRef.whereEqualTo("user_id2", user_id);

        List<ProductReturnDTO> products = new ArrayList<>();
        ApiFuture<QuerySnapshot> future2 = query2.get();

        QuerySnapshot querySnapshot2 = future2.get();

        for (DocumentSnapshot document : querySnapshot2.getDocuments()) {
            Product product = document.toObject(Product.class);
            User user = userService.getUser(product.getUser_id());
            User user2 = userService.getUser(product.getUser_id2());
            ProductReturnDTO productReturnDTO = new ProductReturnDTO(product.getProduct_id(),user.getUsername(),user2.getUsername(),product.getCategory(),product.getBorrow_date(),product.getReturn_date());
            products.add(productReturnDTO);
        }

        return products;
    }

    public void returnProduct(String product_id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection("Product");

        ApiFuture<QuerySnapshot> query = collectionRef.whereEqualTo("product_id", product_id).get();
        QuerySnapshot querySnapshot = query.get();

        if (!querySnapshot.isEmpty()) {
            DocumentReference docRef = querySnapshot.getDocuments().get(0).getReference();

            ApiFuture<WriteResult> updateFuture = docRef.update(
                    "returned", true,
                    "return_date", new Date()
            );
            updateFuture.get();
        }
    }
}
