package com.baro.baroborrow.Service;

import com.baro.baroborrow.Domain.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static final double EARTH_RADIUS = 6371000;

    public List<User> getUsers() throws Exception {
        List<User> list = new ArrayList<>();
        Firestore firestore = FirestoreClient.getFirestore();

        CollectionReference collectionRef = firestore.collection("User");
        List<QueryDocumentSnapshot> documents = collectionRef.get().get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            list.add(document.toObject(User.class));
        }
        return list;
    }

    public User getUser(String user_id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection("User");
        Query query = collectionRef.whereEqualTo("user_id", user_id);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        if (!documents.isEmpty()) {
            // 첫 번째 결과를 User 객체로 반환
            return documents.get(0).toObject(User.class);
        } else {
            return null;  // 해당 user_id가 없을 경우 null 반환
        }
    }

    public void addUser(User user) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection("User");
        collectionRef.document().set(user);
    }

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }


    public void deleteUser(String user_id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection("User");
        Query query = collectionRef.whereEqualTo("user_id", user_id);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            if (document.exists()) {
                collectionRef.document(document.getId()).delete();
            }
        }
    }

    public void updateUserLocation(String user_id, String newAddress, double newLatitude, double newLongitude) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection("User");
        Query query = collectionRef.whereEqualTo("user_id", user_id);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            if (document.exists()) {
                collectionRef.document(document.getId()).update("address", newAddress,
                        "latitude", newLatitude,
                        "longitude", newLongitude);
            }
        }
    }
}
