package com.baro.baroborrow.Controller;

import com.baro.baroborrow.Domain.Point;
import com.baro.baroborrow.Service.PointService;
import com.baro.baroborrow.Service.UserService;
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
public class PointController {
    private final PointService pointService;

    @GetMapping("/point-history/{user_id}")
    public ResponseEntity<List<Point>> pointHistory(@PathVariable String user_id) throws Exception {
        List<Point> list = pointService.getHistory(user_id);
        return ResponseEntity.ok(list);
    }
}
