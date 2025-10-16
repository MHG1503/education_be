package com.mhgjoker.education.system.file.importer;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.Cell;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.mhgjoker.education.system.common.LevelConst;
import com.mhgjoker.education.system.common.QuestionConst;
import com.mhgjoker.education.system.entity.OptionEntity;
import com.mhgjoker.education.system.entity.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import java.util.*;


@Slf4j
public class QuestionImportFile extends BaseImport<QuestionImportFile.QuestionExcelDTO, QuestionEntity>{

    public QuestionImportFile(String action) {
        this.action = action;
    }

    @Override
    public BaseAnalyzerListener<QuestionExcelDTO, QuestionEntity> getListener() {
        return new QuestionAnalyzerListener();
    }

    @Override
    public Class<QuestionExcelDTO> getClazz() {
        return QuestionExcelDTO.class;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuestionExcelDTO {
        @ExcelProperty("Question")
        private String question;

        @ExcelProperty("Mark")
        private String mark;

        @ExcelProperty("Level")
        private String level;

        @ExcelProperty("Correct Answer")
        private String correctAnswer; // 1/2/3/4/vv

        @ExcelIgnore
        private Map<String, String> answers = new LinkedHashMap<>();
    }

    public static class QuestionAnalyzerListener extends BaseAnalyzerListener<QuestionExcelDTO, QuestionEntity>{

        private List<Integer> headerAnswers;

        @Override
        public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {

            headerAnswers = headMap.entrySet().stream()
                    .filter(e -> e.getValue().startsWith("Answer"))
                    .map(Map.Entry::getKey)
                    .toList();
        }

        @Override
        public void invoke(QuestionExcelDTO row, AnalysisContext context) {
            ReadRowHolder rowHolder = context.readRowHolder();
            Map<Integer, Cell> cellMap = rowHolder.getCellMap();
            Map<String, String> answers = new LinkedHashMap<>();
            for (int i = 0; i < headerAnswers.size(); i++) {
                String value = ""; // 🔹 Khai báo ở đây

                ReadCellData<?> cellData = (ReadCellData<?>) cellMap.get(headerAnswers.get(i));

                if (cellData != null) {
                    switch (cellData.getType()) {
                        case NUMBER -> value = String.valueOf(cellData.getNumberValue());
                        case BOOLEAN -> value = String.valueOf(cellData.getBooleanValue());
                        case STRING, EMPTY -> value = cellData.getStringValue();
                        default -> value = "";
                    }
                }
                answers.put("Answer " + (i + 1), value);
            }
            row.setAnswers(answers);
            rows.add(row);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            for (QuestionExcelDTO row : rows){
                QuestionEntity question = new QuestionEntity();
                question.setContent(row.question);

                if(StringUtils.hasText(row.getMark())) {
                    var mark = parseInt(row.getMark());
                    question.setMark(mark == 0 ? 1 : mark);
                }

                var correctAnswer = row.getCorrectAnswer();

                Set<OptionEntity> optionEntities = new HashSet<>();
                for(var answer : row.getAnswers().entrySet()){
                    OptionEntity option = new OptionEntity();
                    option.setContent(answer.getValue());
                    option.setQuestion(question);
                    option.setIsCorrect(answer.getKey().contains(correctAnswer));
                    optionEntities.add(option);
                }
                question.setOptions(optionEntities);

                String level = switch (row.level.toLowerCase()) {
                    case LevelConst.EASY -> LevelConst.EASY;
                    case LevelConst.MEDIUM -> LevelConst.MEDIUM;
                    case LevelConst.HARD -> LevelConst.HARD;
                    default -> LevelConst.MEDIUM;
                };

                question.setLevel(level);

                result.add(question);
            }
        }
    }

    // TODO chưa xong cái này
    public static class UpdateQuestionAnalyzerListener extends BaseAnalyzerListener<QuestionExcelDTO, QuestionEntity>{

        private List<Integer> headerAnswers;

        @Override
        public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {

            headerAnswers = headMap.entrySet().stream()
                    .filter(e -> e.getValue().startsWith("Answer"))
                    .map(Map.Entry::getKey)
                    .toList();
        }

        @Override
        public void invoke(QuestionExcelDTO row, AnalysisContext context) {
            ReadRowHolder rowHolder = context.readRowHolder();
            Map<Integer, Cell> cellMap = rowHolder.getCellMap();
            Map<String, String> answers = new LinkedHashMap<>();
            for (int i = 0; i < headerAnswers.size(); i++) {
                String value = ""; // 🔹 Khai báo ở đây

                ReadCellData<?> cellData = (ReadCellData<?>) cellMap.get(headerAnswers.get(i));

                if (cellData != null) {
                    switch (cellData.getType()) {
                        case NUMBER -> value = String.valueOf(cellData.getNumberValue());
                        case BOOLEAN -> value = String.valueOf(cellData.getBooleanValue());
                        case STRING, EMPTY -> value = cellData.getStringValue();
                        default -> value = "";
                    }
                }
                answers.put("Answer " + (i + 1), value);
            }
            row.setAnswers(answers);
            rows.add(row);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            for (QuestionExcelDTO row : rows){
                QuestionEntity question = new QuestionEntity();

                if(StringUtils.hasText(row.question)){
                    question.setContent(row.question);
                }

                if(StringUtils.hasText(row.getMark())) {
                    var mark = parseInt(row.getMark());
                    question.setMark(mark == 0 ? 1 : mark);
                }

                String correctAnswer = row.getCorrectAnswer().trim();

                Set<OptionEntity> optionEntities = new HashSet<>();
                for(var answer : row.getAnswers().entrySet()){
                    OptionEntity option = new OptionEntity();
                    option.setContent(answer.getValue());
                    option.setQuestion(question);
                    option.setIsCorrect(answer.getKey().contains(correctAnswer));
                    optionEntities.add(option);
                }
                question.setOptions(optionEntities);

                String level = switch (row.level.toLowerCase()) {
                    case LevelConst.EASY -> LevelConst.EASY;
                    case LevelConst.MEDIUM -> LevelConst.MEDIUM;
                    case LevelConst.HARD -> LevelConst.HARD;
                    default -> LevelConst.MEDIUM;
                };

                question.setLevel(level);

                result.add(question);
            }
        }
    }

}
