package com.baro.baroborrow.Service;

import com.baro.baroborrow.DTO.LendingItemServerDto;
import com.baro.baroborrow.domain.Item.LendingItem;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
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

    public ResponseEntity<String> addLendingItem(LendingItemServerDto item) {
        Map<String, Object> inquiryData = new HashMap<>();
        LendingItem newitem = null;
        if (item.getImage() != null && !item.getImage().isEmpty()) {
            try {
                // 이미지 파일을 Base64로 인코딩
                byte[] imageBytes = item.getImage().getBytes();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                //서버에 저장할 엔티티 생성
                newitem = new LendingItem(item, base64Image);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("Failed to process image");
            }
        }

        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection("LendingItems");
        collectionRef.document().set(newitem);
        return null;
    }

    public void deleteLendingItem(String itemId) {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection("LendingItems");
        collectionRef.document(itemId).delete();
    }

}