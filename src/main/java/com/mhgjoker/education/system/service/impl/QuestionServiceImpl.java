package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.dto.request.option.OptionRequest;
import com.mhgjoker.education.system.dto.request.question.QuestionRequest;
import com.mhgjoker.education.system.dto.request.question.SearchQuestionRequest;
import com.mhgjoker.education.system.entity.OptionEntity;
import com.mhgjoker.education.system.entity.QuestionEntity;
import com.mhgjoker.education.system.entity.SubjectEntity;
import com.mhgjoker.education.system.repository.QuestionRepository;
import com.mhgjoker.education.system.repository.SubjectRepository;
import com.mhgjoker.education.system.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public Page<QuestionEntity> list(SearchQuestionRequest request) {
        Pageable pageable = PageRequest.of(request.pageNum, request.pageSize);
        String keyword = request.keyword;
        String type = request.type;
        String level = request.level;
        Integer mark = request.mark;
        Long subjectId = request.subjectId;
        return questionRepository.search(keyword,type,subjectId,level,mark,pageable);
    }

    @Override
    public QuestionEntity detail(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public QuestionEntity saveOrUpdate(QuestionRequest request) {
        Long subjectId = request.getSubjectId();
        Long questionId = request.getId();
        String level = request.getLevel();
        String content = request.getContent();
        Integer mark = request.getMark();

        Set<OptionRequest> optionsReq = request.getOptions();

        SubjectEntity subject = subjectRepository
                .findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay mon hoc"));

        QuestionEntity question;
        if(questionId == null){
            question = new QuestionEntity();
        }else{
            question = questionRepository
                    .findById(questionId)
                    .orElseThrow(() -> new RuntimeException("Khong tim thay cau hoi"));
        }
        question.setSubject(subject);
        question.setLevel(level);
        question.setContent(content);
        question.setMark(mark);

        updateOptions(question,optionsReq);
        return questionRepository.save(question);
    }

    private void updateOptions(QuestionEntity question, Set<OptionRequest> optionsReq) {
        Set<OptionEntity> existingOptions = question.getOptions();

        Map<Long, OptionEntity> existingMap = existingOptions.stream()
                .filter(o -> o.getId() != null)
                .collect(Collectors.toMap(OptionEntity::getId, o -> o));

        Set<OptionEntity> updatedOptions = new HashSet<>();

        for(OptionRequest req : optionsReq){
            OptionEntity option;
            if(req.getId() != null && existingMap.containsKey(req.getId())){
                option = existingMap.get(req.getId());
                option.setContent(req.getContent());
                option.setIsCorrect(req.getIsCorrect());
            } else {
                option = OptionEntity.builder()
                        .content(req.getContent())
                        .isCorrect(req.getIsCorrect())
                        .question(question)
                        .build();
            }
            updatedOptions.add(option);
        }

        // Set mới cho question
        question.getOptions().clear();
        question.getOptions().addAll(updatedOptions);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDeleted = false;
        if (questionRepository.existsById(id)) {
            questionRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public List<QuestionEntity> saveMany(List<QuestionEntity> entities) {
        return questionRepository.saveAll(entities);
    }

    @Override
    public List<QuestionEntity> updateMany(List<QuestionEntity> entities) {
        return questionRepository.saveAll(entities);
    }
}
