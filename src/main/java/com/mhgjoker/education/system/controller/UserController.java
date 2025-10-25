package com.mhgjoker.education.system.controller;

import com.mhgjoker.education.security.common.utils.CurrentUserUtils;
import com.mhgjoker.education.system.dto.request.exam_result.ExamResultRequest;
import com.mhgjoker.education.system.mapper.UserMapper;
import com.mhgjoker.education.system.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final CurrentUserUtils currentUserUtils;
    private final ExamResultService examResultService;
    private final UserMapper userMapper;

    @GetMapping("/profile")
    public ResponseEntity<?> profile(){
        var user = currentUserUtils.getCurrentUser();
        return ResponseEntity.ok(userMapper.entityToResponse(user));
    }

   @GetMapping("/my_exam_results")
   public ResponseEntity<?> myExamResults(@RequestParam(name = "pageNum",required = false,defaultValue = "0") Integer pageNum,
                                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize){
       var userId = currentUserUtils.getCurrentUserId();

       var examResults = examResultService.listByUserId(userId, pageNum, pageSize);

       return ResponseEntity.ok(examResults);
   }

   @GetMapping("/my_exam_result_detail")
   public ResponseEntity<?> myExamResultDetail(@RequestParam("examId") Long examId){
       var userId = currentUserUtils.getCurrentUserId();

       var examResult = examResultService.detailByUserIdAndExamId(userId,examId);

       return ResponseEntity.ok(examResult);
   }

    @PostMapping("/submit_exam")
    public ResponseEntity<?> save(@RequestBody ExamResultRequest request) {
        try {
            var userId = currentUserUtils.getCurrentUser();
            examResultService.submitExam(userId, request);
            return ResponseEntity.ok("Submit bai thanh cong");
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
