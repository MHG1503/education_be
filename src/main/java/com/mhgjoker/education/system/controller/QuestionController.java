package com.mhgjoker.education.system.controller;

import com.mhgjoker.education.system.dto.request.question.QuestionRequest;
import com.mhgjoker.education.system.file.export.QuestionImportFile;
import com.mhgjoker.education.system.mapper.QuestionMapper;
import com.mhgjoker.education.system.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize) {
        var rs = questionService.list(pageNum, pageSize);
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> detail(@RequestParam("id") Long id) {
        var rs = questionService.detail(id);
        return ResponseEntity.ok(rs);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody QuestionRequest request) {
        var entity = questionMapper.requestToEntity(request);
        var rs = questionService.saveOrUpdate(entity);
        return ResponseEntity.ok(rs);
    }

    @PostMapping(path = "/import-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> importFile(@RequestParam("file") MultipartFile file) throws Exception {
        try {
            questionService.importFile(file);
            return ResponseEntity.ok("Import dữ liệu thành công");

        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Long id) {
        var rs = questionService.deleteById(id);
        if(!rs){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok("Xoa thanh cong");
    }
}
