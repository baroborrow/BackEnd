package com.baro.baroborrow.Service;

import com.baro.baroborrow.Domain.Board;
import com.baro.baroborrow.Domain.User;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public List<Board> getBoardsMostViewed(String user_id) throws Exception {
        List<Board> boards = new ArrayList<>();
        Firestore firestore = FirestoreClient.getFirestore();
        UserService userService = new UserService();

        CollectionReference collectionRef = firestore.collection("Board");
        Query query = collectionRef.orderBy("views", Query.Direction.DESCENDING);

        List<QueryDocumentSnapshot> documents = query.get().get().getDocuments();
        User user2 = userService.getUser(user_id);

        int cnt = 0;
        for (QueryDocumentSnapshot document : documents) {
            Board board = document.toObject(Board.class);
            User user = userService.getUser(board.getUser_id());

        if (UserService.calculateDistance(user.getLatitude(), user.getLongitude(), user2.getLatitude(), user2.getLongitude()) <= 1 && !board.getUser_id().equals(user_id) && cnt<2) {
                boards.add(board);
                cnt++;
            }
        }
        return boards;
    }

    public List<Board> getBoardsByCategory(String user_id, String category) throws Exception {
        List<Board> boards = new ArrayList<>();
        Firestore firestore = FirestoreClient.getFirestore();
        UserService userService = new UserService();

        CollectionReference collectionRef = firestore.collection("Board");
        Query query = collectionRef.whereEqualTo("category", category);
        List<QueryDocumentSnapshot> documents = query.get().get().getDocuments();
        User user2 = userService.getUser(user_id);
        for (QueryDocumentSnapshot document : documents) {
            Board board = document.toObject(Board.class);
            User user = userService.getUser(board.getUser_id());
            if(UserService.calculateDistance(user.getLatitude(),user.getLongitude(),user2.getLatitude(),user2.getLongitude()) <= 1 && !board.getUser_id().equals(user_id)) {
                boards.add(document.toObject(Board.class));
            }
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
