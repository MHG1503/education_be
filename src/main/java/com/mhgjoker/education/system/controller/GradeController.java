package com.mhgjoker.education.system.controller;

import com.mhgjoker.education.system.dto.request.grade.AssignSubjectToGradeRequest;
import com.mhgjoker.education.system.dto.request.grade.GradeRequest;
import com.mhgjoker.education.system.mapper.GradeMapper;
import com.mhgjoker.education.system.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;
    private final GradeMapper gradeMapper;

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize){
        var rs = gradeService.list(pageNum, pageSize);

        return ResponseEntity.ok(rs);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> detail(@RequestParam("id") Long id){
        var rs = gradeService.detail(id);
        return ResponseEntity.ok(rs);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody GradeRequest request){
        var entity = gradeMapper.requestToEntity(request);

        var rs = gradeService.saveOrUpdate(entity);

        return ResponseEntity.ok(rs);
    }

    @PostMapping("assign-subject")
    public ResponseEntity<?> assignSubjectToGrade(@RequestBody AssignSubjectToGradeRequest request){
        if(request.gradeId == null || request.subjectId == null){
            throw new RuntimeException("Thieu thong tin dau vao cua grade hoac subject");
        }
        try {
            gradeService.assignSubject(request.gradeId, request.subjectId);
            return ResponseEntity.ok("Gan thanh cong mon hoc vao lop");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Gan du lieu that bai!");
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> remove(@RequestParam("id") Long id){
        var rs = gradeService.deleteById(id);
        if(rs){
            return ResponseEntity.ok("Xoa du lieu thanh cong");
        }
        return ResponseEntity.internalServerError().body("Xoa that bai!");
    }
}
