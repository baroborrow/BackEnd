package com.baro.baroborrow.Controller;

import com.baro.baroborrow.DTO.BoardAddDTO;
import com.baro.baroborrow.Domain.Board;
import com.baro.baroborrow.Enum.CategoryId;
import com.baro.baroborrow.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/all-boards")
    public ResponseEntity<List<Board>> getBoards() throws Exception {
        List<Board> boards = boardService.getBoards();
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/find-board/{category}")
    public ResponseEntity<List<Board>> getBoard(@PathVariable String category) throws Exception {
        List<Board> boards = boardService.getBoardsByCategory(category);
        return ResponseEntity.ok(boards);
    }

    @PostMapping("/add-board")
    public void addBoard(@RequestBody BoardAddDTO boardAddDTO) throws Exception {
        Board board = new Board(boardAddDTO.getUser_id(),boardAddDTO.getTitle(), boardAddDTO.getStart_date(), boardAddDTO.getEnd_date(),boardAddDTO.getPrice(), boardAddDTO.getCategory(),boardAddDTO.getOpen_chat(),boardAddDTO.getDescription(),boardAddDTO.getWarning());
        boardService.addBoard(board);
    }

    @PatchMapping("/increase-views/{board_id}")
    public void increaseView(@PathVariable String board_id) throws Exception {
        boardService.increaseView(board_id);
    }
}
