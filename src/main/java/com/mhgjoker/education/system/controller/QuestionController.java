package com.mhgjoker.education.system.controller;

import com.mhgjoker.education.system.dto.request.question.QuestionRequest;
import com.mhgjoker.education.system.dto.request.question.SearchQuestionRequest;
import com.mhgjoker.education.system.file.importer.QuestionImporter;
import com.mhgjoker.education.system.mapper.QuestionMapper;
import com.mhgjoker.education.system.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final QuestionImporter questionImporter;

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam(name = "keyword", required = false) String keyword,
                                  @RequestParam(name = "subjectId", required = false) Long subjectId,
                                  @RequestParam(name = "level", required = false) String level,
                                  @RequestParam(name = "mark", required = false) Integer mark,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        SearchQuestionRequest request = new SearchQuestionRequest(keyword, subjectId, level, mark, pageNum, pageSize);
        var rs = questionService.list(request);
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> detail(@RequestParam("id") Long id) {
        var rs = questionService.detail(id);
        return ResponseEntity.ok(rs);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody QuestionRequest request) {
        var rs = questionMapper
                .entityToResponse(questionService.saveOrUpdate(request));
        return ResponseEntity.ok(rs);
    }

    @PostMapping(path = "/import-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> importFile(@RequestParam("file") MultipartFile file,
                                        @RequestParam("subject") String subject,
                                        @RequestParam("action") String action) throws Exception {
        try {
            Map<String, Integer> rs = questionImporter.importFile(subject,action,file);
            var successCount = rs.get("success");
            var totalCount = rs.get("total");
            return ResponseEntity.ok("Import dữ liệu thành công " + successCount + " / " + totalCount);
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
