package com.mhgjoker.education.system.controller;

import com.mhgjoker.education.system.dto.request.option.OptionRequest;
import com.mhgjoker.education.system.mapper.OptionMapper;
import com.mhgjoker.education.system.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/options")
@RequiredArgsConstructor
public class OptionController {

    private final OptionService optionService;

    private final OptionMapper optionMapper;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody OptionRequest request){
        var rs = optionService.saveOrUpdate(request);

        return ResponseEntity.ok(rs);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> remove(@RequestParam("id") Long id){
        var rs = optionService.deleteById(id);
        if(rs){
            return ResponseEntity.ok("Xoa du lieu thanh cong");
        }
        return ResponseEntity.internalServerError().body("Xoa that bai!");
    }
}
