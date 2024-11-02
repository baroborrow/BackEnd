package com.baro.baroborrow.Service;

import com.baro.baroborrow.domain.Item.LendingItem;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class LendingItemService {
    public List<LendingItem> getLendingItems() throws ExecutionException, InterruptedException {
        List<LendingItem> list = new ArrayList<>();
        //Firestore 인스턴스 반환해서 FireStore 에 접근하기
        Firestore firestore = FirestoreClient.getFirestore();

        //Firestore에서 "LendingItems"라는 컬렉션을 참조 (컬렉션은 데이터의 집합, MySql의 테이블에 대응)
        CollectionReference collectionRef = firestore.collection("LendingItems");
        List<QueryDocumentSnapshot> documents = collectionRef.get().get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            list.add(document.toObject(LendingItem.class));
        }
        return list;
    }

    public void addLendingItem(LendingItem item) {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection("LendingItems");
        collectionRef.document().set(item);
    }

    public void deleteLendingItem(String itemId) {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection("LendingItems");
        collectionRef.document(itemId).delete();
    }

}