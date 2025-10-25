package com.mhgjoker.education.system.controller;

import com.mhgjoker.education.system.entity.EducationLevelEntity;
import com.mhgjoker.education.system.service.EducationLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/education_level")
@RequiredArgsConstructor
public class EducationLevelController {

    private final EducationLevelService educationLevelService;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        var rs = educationLevelService.list();
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> detail(@RequestParam("id") Long id) {
        var rs = educationLevelService.detail(id);
        return ResponseEntity.ok(rs);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody EducationLevelEntity entity) {
        var rs = educationLevelService.saveOrUpdate(entity);
        return ResponseEntity.ok(rs);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Long id) {
        var rs = educationLevelService.deleteById(id);
        if(!rs){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok("Xoa thanh cong");
    }

}
