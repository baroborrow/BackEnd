package com.baro.baroborrow.Controller;

import com.baro.baroborrow.Domain.Board;
import com.baro.baroborrow.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
