package com.mhgjoker.education.system.controller;

import com.mhgjoker.education.security.common.utils.CurrentUserUtils;
import com.mhgjoker.education.system.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final CurrentUserUtils currentUserUtils;
    private final ExamResultService examResultService;

    @GetMapping("/profile")
    public ResponseEntity<?> profile(){
        var user = currentUserUtils.getCurrentUser();
        return ResponseEntity.ok(user);
    }

   @GetMapping("/my_exam_results")
   public ResponseEntity<?> myExamResults(@RequestParam("pageNum") Integer pageNum,
                                          @RequestParam("pageSize") Integer pageSize){
       var userId = currentUserUtils.getCurrentUserId();

       var examResults = examResultService.listByUserId(userId, pageNum, pageSize);

       return ResponseEntity.ok(examResults);
   }
}
