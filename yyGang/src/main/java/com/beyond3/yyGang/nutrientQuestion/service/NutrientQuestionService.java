package com.beyond3.yyGang.nutrientQuestion.service;

import com.beyond3.yyGang.common.QuestionBoardException;
import com.beyond3.yyGang.common.exception.message.ExceptionMessage;
import com.beyond3.yyGang.nsupplement.NSupplement;
import com.beyond3.yyGang.nsupplement.repository.NSupplementRepository;
import com.beyond3.yyGang.nutrientQuestion.NutrientQuestion;
import com.beyond3.yyGang.nutrientQuestion.repository.NutrientQuestionRepository;
import com.beyond3.yyGang.nutrientQuestion.dto.NutrientQuestionRequestDto;
import com.beyond3.yyGang.nutrientQuestion.dto.NutrientQuestionResponseDto;
import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class NutrientQuestionService {

    private final NutrientQuestionRepository nutrientQuestionRepository;
    private final UserRepository userRepository;
    private final NSupplementRepository nSupplementRepository;


    @Transactional
    public void saveNquestion(NutrientQuestionRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new QuestionBoardException(ExceptionMessage.NOT_FOUND_USER));
        NSupplement supplement = nSupplementRepository.findById(requestDto.getSupplementId()).orElseThrow(()->new QuestionBoardException(ExceptionMessage.NOT_FOUND_SUPPLEMENT));

        if(requestDto.getQContent().isEmpty()){
            throw new QuestionBoardException(ExceptionMessage.EMPTY_CONTENT);
        }

        NutrientQuestion question = requestDto.toEntity(supplement,user);
        nutrientQuestionRepository.save(question);
    }


    @Transactional
    public Page<NutrientQuestionResponseDto> getAllNqboard(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<NutrientQuestion> Nqboardpage = nutrientQuestionRepository.findAll(pageable);

        return Nqboardpage.map(NutrientQuestionResponseDto::new);
    }

    public NutrientQuestionResponseDto getNqboardById(Long nqboardId) {

        Optional<NutrientQuestion> Nqboard = nutrientQuestionRepository.findById(nqboardId);

        if(Nqboard.isEmpty()){
            throw new QuestionBoardException(ExceptionMessage.NOT_FOUND_QUESTION_BOARD);
        }

        return Nqboard.map(NutrientQuestionResponseDto::new).orElseThrow(()-> new QuestionBoardException(ExceptionMessage.NOT_FOUND_QUESTION_BOARD));
    }

    @Transactional
    public void updateNqboard(Long nqboardId, NutrientQuestionRequestDto requestDto) {

        NutrientQuestion nutrientQuestion = nutrientQuestionRepository.findById(nqboardId).orElseThrow(()-> new QuestionBoardException(ExceptionMessage.NOT_FOUND_QUESTION_BOARD));

        if (requestDto.getQContent().isEmpty()) {
            throw new QuestionBoardException(ExceptionMessage.EMPTY_TITLE);
        }

        nutrientQuestion.update( requestDto.getQContent());
    }


    public void deleteQboard(Long nqboardId) {
        NutrientQuestion nutrientQuestion = nutrientQuestionRepository.findById(nqboardId).orElseThrow(()-> new QuestionBoardException(ExceptionMessage.NOT_FOUND_QUESTION_BOARD));
        nutrientQuestionRepository.delete(nutrientQuestion);
    }
}
