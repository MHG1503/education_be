package com.mhgjoker.education.system.file.importer;

import com.mhgjoker.education.system.entity.QuestionEntity;
import com.mhgjoker.education.system.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuestionImporter {

    private final QuestionService questionService;

    public Map<String, Integer> importFile(String action, MultipartFile file) {
        int successCount = 0;
        int totalCount = 0;
        QuestionImportFile questionImporter = getQuestionImporter( action);
        if (questionImporter == null) {
            log.error("Không hỗ trợ ");
            return Map.of(
                    "success",successCount,
                    "total",totalCount
            );
        }
        try {
            List<QuestionEntity> records = questionImporter.importFile(file.getInputStream());
            totalCount = records.size();
            int rowIndex = 1;
            List<QuestionEntity> validQuestions = new ArrayList<>();
            for(var question : records) {
                if(validateRow(question)) {
                    validQuestions.add(question);
                }
            }

            int chunkSize = 100;
            for (int i = 0; i < validQuestions.size(); i += chunkSize) {
                try {
                    int end = Math.min(i + chunkSize, validQuestions.size());
                    List<QuestionEntity> chunk = validQuestions.subList(i, end);
                    List<QuestionEntity> savedItems;

                    if (action.equals("update")) {
                        savedItems = questionService.updateMany(chunk);
                    } else { // append
                        savedItems = questionService.saveMany(chunk);
                    }

                    successCount += savedItems.size();
                } catch (Exception e) {
                    if (e.getMessage().contains("duplicate key")) {
                        continue;// lỗi trùng khoá unique thì bỏ qua
                    } else {
                        log.error("Lỗi khi lưu batch từ dòng {} đến dòng {}: {}", i + 1,
                                Math.min(i + chunkSize, validQuestions.size()), e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            log.error("Lỗi khi đọc file: {}", e.getMessage());
        } finally {
            try {
                file.getInputStream().close();
            } catch (IOException e) {
                log.error("Lỗi khi đóng InputStream: {}", e.getMessage());
            }
        }
        return Map.of(
                "success",successCount,
                "total",totalCount
        );
    }

    private QuestionImportFile getQuestionImporter(String action) {
          return new QuestionImportFile(action);
    }

    private boolean validateRow(QuestionEntity question){
        return StringUtils.hasText(question.getContent())
                && StringUtils.hasText(question.getType());
    }
}
