package com.baro.baroborrow.Service;

import com.baro.baroborrow.Domain.Point;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PointService {
    public List<Point> getHistory(String userId) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();

        //User 테이블에서 현재 user 불러오기
        CollectionReference userCollectionRef = firestore.collection("User");
        QuerySnapshot userQuerySnapshot = userCollectionRef.whereEqualTo("user_id", userId).get().get();

        if (userQuerySnapshot.isEmpty()) {
            throw new NoSuchElementException("User not found for userId: " + userId);
        }

        //Point 테이블에서 현재 user의 point 정보 모두 불러오기
        CollectionReference pointCollectionRef = firestore.collection("Point");
        QuerySnapshot pointQuerySnapshot = pointCollectionRef.whereEqualTo("user_id", userId).get().get();

        List<Point> points = new ArrayList<>();
        for (QueryDocumentSnapshot document : pointQuerySnapshot.getDocuments()) {
            points.add(document.toObject(Point.class));
        }

        return points;
    }
}
