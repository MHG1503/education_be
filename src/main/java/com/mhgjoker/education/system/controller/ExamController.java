package com.mhgjoker.education.system.controller;

import com.mhgjoker.education.system.dto.request.exam.ExamRequest;
import com.mhgjoker.education.system.mapper.ExamMapper;
import com.mhgjoker.education.system.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;
    private final ExamMapper examMapper;

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize) {
        var rs = examService.list(pageNum, pageSize);
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> detail(@RequestParam("id") Long id) {
        var rs = examService.detail(id);
        return ResponseEntity.ok(rs);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ExamRequest request) {
        var entity = examMapper.requestToEntity(request);
        var rs = examService.saveOrUpdate(entity);
        return ResponseEntity.ok(rs);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Long id) {
        var rs = examService.deleteById(id);
        if(!rs){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok("Xoa thanh cong");
    }
}
