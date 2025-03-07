package com.beyond3.yyGang.nutrientAnswer.service;

import com.beyond3.yyGang.answer.repository.AnswerRepository;
import com.beyond3.yyGang.common.AnswerException;
import com.beyond3.yyGang.common.QuestionBoardException;
import com.beyond3.yyGang.common.exception.message.ExceptionMessage;
import com.beyond3.yyGang.nutrientAnswer.NAnswer;
import com.beyond3.yyGang.nutrientAnswer.dto.NurtientAnswerRequestDto;
import com.beyond3.yyGang.nutrientAnswer.dto.NutrientAnswerResponseDto;
import com.beyond3.yyGang.nutrientAnswer.repository.NurtientAnswerRepository;
import com.beyond3.yyGang.nutrientQuestion.NutrientQuestion;
import com.beyond3.yyGang.nutrientQuestion.dto.NutrientQuestionRequestDto;
import com.beyond3.yyGang.nutrientQuestion.repository.NutrientQuestionRepository;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NurtientAnswerService {

    private final NutrientQuestionRepository nutrientQuestionRepository;
    private final UserRepository userRepository;
    private final NurtientAnswerRepository nurtientAnswerRepository;

    @Transactional
    public void saveAnswer(Long nqboardId, NurtientAnswerRequestDto requestDto) {

        NutrientQuestion nqboard = nutrientQuestionRepository.findById(nqboardId).orElseThrow(() -> new AnswerException(ExceptionMessage.NOT_FOUND_QUESTION_BOARD));

        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(()->new AnswerException(ExceptionMessage.NOT_FOUND_USER));

        if(requestDto.getAnswerContent().isEmpty()){
            throw new AnswerException(ExceptionMessage.EMPTY_CONTENT);
        }
        NAnswer nAnswer = requestDto.toEntity(user);

        nurtientAnswerRepository.save(nAnswer);
    }

    @Transactional
    public NutrientAnswerResponseDto getAnswerById(Long NqboardId) {

        NutrientQuestion nutrientQuestion = nutrientQuestionRepository.findById(NqboardId).orElseThrow(() -> new AnswerException(ExceptionMessage.NOT_FOUND_QUESTION_BOARD));

        return NutrientAnswerResponseDto.builder()
                .nAnswer(nutrientQuestion.getNAnswer())
                .build();
    }

    public void updateAnswer(Long nqboardId, NurtientAnswerRequestDto requestDto) {
        NAnswer nAnswer = nurtientAnswerRepository.findById(nqboardId).orElseThrow(() -> new AnswerException(ExceptionMessage.NOT_FOUND_ANSWER));

        nAnswer.update(requestDto.getAnswerContent());
    }

    public void deleteAnswer(Long nqboardId) {
        NAnswer nAnswer = nurtientAnswerRepository.findById(nqboardId).orElseThrow(()-> new AnswerException(ExceptionMessage.NOT_FOUND_ANSWER));
        nurtientAnswerRepository.delete(nAnswer);
    }
}


