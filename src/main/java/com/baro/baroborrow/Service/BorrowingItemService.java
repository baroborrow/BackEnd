package com.baro.baroborrow.Service;

import com.baro.baroborrow.domain.Item.BorrowingItem;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowingItemService {
    public void addBorrowingItem(BorrowingItem item) {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection("BorrowingItems");
        collectionRef.document().set(item);
    }
    public List<BorrowingItem> getBorrowingItems() throws Exception {
        List<BorrowingItem> list = new ArrayList<>();
        Firestore firestore = FirestoreClient.getFirestore();

        CollectionReference collectionRef = firestore.collection("BorrowingItems");
        List<QueryDocumentSnapshot> documents = collectionRef.get().get().getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            list.add(document.toObject(BorrowingItem.class));
        }

        return list;
    }

    public void deleteBorrowingItem(String itemId) {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection("BorrowingItems");
        collectionRef.document(itemId).delete();
    }
}
