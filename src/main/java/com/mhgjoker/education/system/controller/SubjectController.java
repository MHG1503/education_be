package com.mhgjoker.education.system.controller;

import com.mhgjoker.education.system.dto.request.subject.SubjectRequest;
import com.mhgjoker.education.system.entity.SubjectEntity;
import com.mhgjoker.education.system.mapper.SubjectMapper;
import com.mhgjoker.education.system.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper;

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize) {
        var rs = subjectService.list(pageNum, pageSize);
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> detail(@RequestParam("id") Long id) {
        var rs = subjectService.detail(id);
        return ResponseEntity.ok(rs);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SubjectRequest request) {
        var entity = subjectMapper.requestToEntity(request);
        var rs = subjectService.saveOrUpdate(entity);
        return ResponseEntity.ok(rs);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Long id) {
        var rs = subjectService.deleteById(id);
        if(!rs){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok("Xoa thanh cong");
    }
}
