package com.baro.baroborrow.Service;

import com.baro.baroborrow.Domain.Board;
import com.google.cloud.firestore.*;
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

    public void addBoard(Board board) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection("Board");
        DocumentReference docRef = collectionRef.add(board).get();

        String boardId = docRef.getId();
        board.setBoard_id(boardId);

        docRef.set(board);
    }

    public void increaseView(String board_id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference docRef = firestore.collection("Board").document(board_id);

        docRef.update("views", FieldValue.increment(1));
    }
}
