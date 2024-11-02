package com.baro.baroborrow.Service;

import com.baro.baroborrow.Domain.Board;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    public List<Board> getBoards() throws Exception {
        List<Board> boards = new ArrayList<>();
        Firestore firestore = FirestoreClient.getFirestore();

        CollectionReference collectionRef = firestore.collection("Board");
        List<QueryDocumentSnapshot> documents = collectionRef.get().get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            boards.add(document.toObject(Board.class));
        }
        return boards;
    }

    public List<Board> getBoardsByCategory(String category) throws Exception {
        List<Board> boards = new ArrayList<>();
        Firestore firestore = FirestoreClient.getFirestore();

        CollectionReference collectionRef = firestore.collection("Board");
        Query query = collectionRef.whereEqualTo("category", category);
        List<QueryDocumentSnapshot> documents = query.get().get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            boards.add(document.toObject(Board.class));
        }
        return boards;
    }
}
