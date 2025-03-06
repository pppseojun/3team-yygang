package com.beyond3.yyGang.nutrientQuestion;

import com.beyond3.yyGang.common.QuestionBoardException;
import com.beyond3.yyGang.common.exception.message.ExceptionMessage;
import com.beyond3.yyGang.nsupplement.NSupplement;
import com.beyond3.yyGang.nsupplement.repository.NSupplementRepository;
import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
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

        return Nqboard.map(NutrientQuestionResponseDto::new).orElseThrow(()-> new QuestionBoardException(ExceptionMessage.NOT_FOUND_QUESTION_BOARD));
    }


}
