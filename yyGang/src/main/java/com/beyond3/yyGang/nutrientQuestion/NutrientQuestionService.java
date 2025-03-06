package com.beyond3.yyGang.nutrientQuestion;

import com.beyond3.yyGang.common.QuestionBoardException;
import com.beyond3.yyGang.common.exception.message.ExceptionMessage;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class NutrientQuestionService {

    private final NutrientQuestionRepository nutrientQuestionRepository;
    private final UserRepository userRepository;


    public void saveNquestion(NutrientQuestionRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new QuestionBoardException(ExceptionMessage.NOT_FOUND_USER));
    }
}
