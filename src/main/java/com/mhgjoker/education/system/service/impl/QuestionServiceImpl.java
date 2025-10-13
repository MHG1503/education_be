package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.entity.QuestionEntity;
import com.mhgjoker.education.system.repository.QuestionRepository;
import com.mhgjoker.education.system.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public Page<QuestionEntity> list(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return questionRepository.findAll(pageable);
    }

    @Override
    public QuestionEntity detail(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public QuestionEntity saveOrUpdate(QuestionEntity QuestionEntity) {
        return questionRepository.save(QuestionEntity);
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
