package com.baro.baroborrow.Controller;

import com.baro.baroborrow.DTO.BoardBorrowDTO;
import com.baro.baroborrow.Domain.BoardBorrow;
import com.baro.baroborrow.Service.BoardBorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardBorrowController {
    private final BoardBorrowService boardBorrowService;

    @GetMapping("/all-board-borrow")
    public ResponseEntity<List<BoardBorrow>> getBoardBorrows() throws Exception{
        List<BoardBorrow> boards = boardBorrowService.getBoardBorrows();
        return ResponseEntity.ok(boards);
    }

    @PostMapping("/add-board-borrow")
    public void addBoardBorrow(@RequestBody BoardBorrowDTO boardBorrowDTO) throws Exception{
        BoardBorrow boardBorrow = new BoardBorrow(boardBorrowDTO.getUser_id(),boardBorrowDTO.getTitle(),boardBorrowDTO.getStart_date(),boardBorrowDTO.getEnd_date(),boardBorrowDTO.getOpen_chat());
        boardBorrowService.addBoardBorrow(boardBorrow);
    }
}
