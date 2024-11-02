package com.baro.baroborrow.Service;

import com.baro.baroborrow.Domain.Board;
import com.baro.baroborrow.Domain.BoardBorrow;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardBorrowService {

    public List<BoardBorrow> getBoardBorrows() throws Exception{
        List<BoardBorrow> boards = new ArrayList<>();
        Firestore firestore = FirestoreClient.getFirestore();

        CollectionReference collectionRef = firestore.collection("BoardBorrow");
        List<QueryDocumentSnapshot> documents = collectionRef.get().get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            boards.add(document.toObject(BoardBorrow.class));
        }
        return boards;
    }

    public void addBoardBorrow(BoardBorrow boardBorrow) throws Exception{
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection("BoardBorrow");
        DocumentReference docRef = collectionRef.add(boardBorrow).get();

        String boardId = docRef.getId();
        boardBorrow.setBoard_id(boardId);

        docRef.set(boardBorrow);
    }
}
