package com.beyond3.yyGang.answer.service;

import com.beyond3.yyGang.answer.Answer;
import com.beyond3.yyGang.answer.AnswerLike;
import com.beyond3.yyGang.answer.AnswerLikedId;
import com.beyond3.yyGang.answer.repository.AnswerLikeRepository;
import com.beyond3.yyGang.answer.repository.AnswerRepository;
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
        User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new IllegalArgumentException("답변을 찾을 수 없습니다."));
        AnswerLikedId likedId = new AnswerLikedId(user.getUserId(), answerId);

        if(answerLikeRepository.existsById(likedId)){
            throw  new IllegalStateException("이미 좋아요 눌렀음");
        }
        AnswerLike like = new AnswerLike(likedId,user,answer);
        answerLikeRepository.save(like);
    }

    @Transactional
    public void unLikeAnswer(Long userId, Long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 답글인데여...."));
        User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        AnswerLike answerLike = answerLikeRepository.findByUserAndAnswer(user, answer).orElseThrow(()-> new IllegalArgumentException("좋아요를 누른적이 없는데요..."));

        answerLikeRepository.delete(answerLike);
    }
}
