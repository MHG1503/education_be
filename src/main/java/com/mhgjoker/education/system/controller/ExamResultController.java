package com.mhgjoker.education.system.controller;

import com.mhgjoker.education.security.common.utils.CurrentUserUtils;
import com.mhgjoker.education.system.dto.request.exam_result.ExamResultRequest;
import com.mhgjoker.education.system.entity.UserEntity;
import com.mhgjoker.education.system.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/exam_result")
@RequiredArgsConstructor
public class ExamResultController {

    private final ExamResultService examResultService;
    private final CurrentUserUtils currentUserUtils;

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize) {
        var rs = examResultService.list(pageNum, pageSize);
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/list_by_user")
    @PreFilter("hasRole('USER')")
    public ResponseEntity<?> listByUserId(@RequestParam("pageNum") Integer pageNum,
                                          @RequestParam("pageSize") Integer pageSize) {
        UserEntity user = currentUserUtils.getCurrentUser();
        var rs = examResultService.listByUserId(user.getId(), pageNum, pageSize);
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/list_by_admin")
    @PreFilter("hasRole('ADMIN')")
    public ResponseEntity<?> listByAdminRoleAndUserId(@RequestParam("userId") Long userId,
                                                      @RequestParam("pageNum") Integer pageNum,
                                                      @RequestParam("pageSize") Integer pageSize) {
        var rs = examResultService.listByUserId(userId, pageNum, pageSize);
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> detail(@RequestParam("id") Long id) {
        var rs = examResultService.detail(id);
        return ResponseEntity.ok(rs);
    }

    @PostMapping("/submit_exam")
    public ResponseEntity<?> save(@RequestBody ExamResultRequest request) {
        try {
            examResultService.submitExam(request);
            return ResponseEntity.ok("Submit bai thanh cong");
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Long id) {
        var rs = examResultService.deleteById(id);
        if(!rs){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok("Xoa thanh cong");
    }
}
