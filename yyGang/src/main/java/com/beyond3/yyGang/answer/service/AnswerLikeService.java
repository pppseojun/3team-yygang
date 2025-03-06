package com.beyond3.yyGang.answer.service;

import com.beyond3.yyGang.answer.Answer;
import com.beyond3.yyGang.answer.AnswerLike;
import com.beyond3.yyGang.answer.AnswerLikedId;
import com.beyond3.yyGang.answer.repository.AnswerLikeRepository;
import com.beyond3.yyGang.answer.repository.AnswerRepository;
import com.beyond3.yyGang.common.AnswerException;
import com.beyond3.yyGang.common.AnswerLikeException;
import com.beyond3.yyGang.common.exception.message.ExceptionMessage;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import com.beyond3.yyGang.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerLikeService {

    private final AnswerLikeRepository likeRepository;
    private final AnswerRepository answerRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final AnswerLikeRepository answerLikeRepository;

    @Transactional
    public void likeAnswer(Long answerId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new AnswerLikeException(ExceptionMessage.NOT_FOUND_USER));
        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new AnswerLikeException(ExceptionMessage.NOT_FOUND_ANSWER));
        AnswerLikedId likedId = new AnswerLikedId(user.getUserId(), answerId);

        if(answerLikeRepository.existsById(likedId)){
            throw  new AnswerLikeException(ExceptionMessage.ALREADY_PRESSED);
        }
        AnswerLike like = new AnswerLike(likedId,user,answer);
        answerLikeRepository.save(like);
    }

    @Transactional
    public void unLikeAnswer(Long userId, Long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new AnswerException(ExceptionMessage.NOT_FOUND_ANSWER));
        User user = userRepository.findById(userId).orElseThrow(()->new AnswerException(ExceptionMessage.NOT_FOUND_USER));

        AnswerLike answerLike = answerLikeRepository.findByUserAndAnswer(user, answer).orElseThrow(()-> new AnswerLikeException(ExceptionMessage.NOT_FOUND_ANSWER_LIKE));

        answerLikeRepository.delete(answerLike);
    }
}
