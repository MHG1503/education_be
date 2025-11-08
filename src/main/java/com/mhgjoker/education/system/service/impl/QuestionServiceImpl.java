package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.dto.request.option.OptionRequest;
import com.mhgjoker.education.system.dto.request.question.QuestionRequest;
import com.mhgjoker.education.system.dto.request.question.SearchQuestionRequest;
import com.mhgjoker.education.system.dto.response.grade.GradeResponse;
import com.mhgjoker.education.system.dto.response.question.QuestionResponse;
import com.mhgjoker.education.system.entity.*;
import com.mhgjoker.education.system.integration.MinioChannel;
import com.mhgjoker.education.system.mapper.QuestionMapper;
import com.mhgjoker.education.system.repository.GradeRepository;
import com.mhgjoker.education.system.repository.QuestionRepository;
import com.mhgjoker.education.system.repository.SubjectRepository;
import com.mhgjoker.education.system.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private final GradeRepository gradeRepository;
    private final QuestionMapper questionMapper;
    private final MinioChannel minioChannel;

    @Override
    public PaginatedResponse<QuestionResponse> list(SearchQuestionRequest request) {
        Pageable pageable = PageRequest.of(request.pageNum, request.pageSize);
        String keyword = request.keyword;
        String level = request.level;
        Integer mark = request.mark;
        Long subjectId = request.subjectId;
        var rs = questionRepository.search(keyword,subjectId,level,mark,pageable);

        return new PaginatedResponse<>(
                rs.getContent().stream().map(questionMapper::entityToResponse).toList(),
                rs.getTotalPages(),
                rs.getNumber(),
                rs.getTotalElements()
        );
    }

    @Override
    public QuestionResponse detail(Long id) {
        return questionMapper
                .entityToResponse(questionRepository
                        .findById(id).
                        orElse(null)
                );
    }

    @Override
    public QuestionResponse saveOrUpdate(QuestionRequest request) {
        Long subjectId = request.getSubjectId();
        Long questionId = request.getId();
        String level = request.getLevel();
        String content = request.getContent();
        Integer mark = request.getMark();
        String imageUrl = request.getImageUrl();
        Set<OptionRequest> optionsReq = request.getOptions();

        SubjectEntity subject = subjectRepository
                .findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay mon hoc"));

        GradeEntity grade = gradeRepository
                .findById(request.getGradeId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khối học"));


        QuestionEntity question;
        if(questionId == null){
            question = new QuestionEntity();
        }else{
            question = questionRepository
                    .findById(questionId)
                    .orElseThrow(() -> new RuntimeException("Khong tim thay cau hoi"));
        }
        String folder = subject.getSubjectName() + "/" + grade.getGradeName() + "/" + content;

        question.setSubject(subject);
        question.setLevel(level);
        question.setContent(content);
        question.setMark(mark);
        question.setImageUrl(imageUrl);
        updateOptions(question,optionsReq);
        return questionMapper.entityToResponse(questionRepository.save(question));
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
                option.setImageUrl(req.getImageUrl());
            } else {
                option = OptionEntity.builder()
                        .content(req.getContent())
                        .isCorrect(req.getIsCorrect())
                        .question(question)
                        .imageUrl(req.getImageUrl())
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
    public List<QuestionResponse> saveMany(List<QuestionEntity> entities) {
        return questionRepository.saveAll(entities).stream().map(questionMapper::entityToResponse).toList();
    }

    @Override
    public List<QuestionResponse> updateMany(List<QuestionEntity> entities) {
        return questionRepository.saveAll(entities).stream().map(questionMapper::entityToResponse).toList();
    }
}
