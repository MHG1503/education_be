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
    public ResponseEntity<?> list(@RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                  @RequestParam(name = "pageSize",required = false, defaultValue = "5") Integer pageSize) {
        var rs = examResultService.list(pageNum, pageSize);
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/list_by_admin")
    @PreFilter("hasRole('ADMIN')")
    public ResponseEntity<?> listByAdminRoleAndUserId(@RequestParam("userId") Long userId,
                                                      @RequestParam(name = "pageNum",required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        var rs = examResultService.listByUserId(userId, pageNum, pageSize);
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/detail_by_admin")
    @PreFilter("hasRole('ADMIN')")
    public ResponseEntity<?> detail(@RequestParam("userId") Long userId,
                                    @RequestParam("examId") Long examId) {
        var rs = examResultService.detailByUserIdAndExamId(userId, examId);
        return ResponseEntity.ok(rs);
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
