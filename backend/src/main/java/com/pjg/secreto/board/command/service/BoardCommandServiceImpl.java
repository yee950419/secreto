package com.pjg.secreto.board.command.service;

import com.pjg.secreto.board.command.repository.BoardCommandRepository;
import com.pjg.secreto.board.common.entity.Board;
import com.pjg.secreto.board.common.entity.Liked;
import com.pjg.secreto.board.common.entity.Reply;
import com.pjg.secreto.board.query.repository.BoardQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCommandServiceImpl implements BoardCommandService {
    private BoardQueryRepository queryRepository;
    private BoardCommandRepository commandRepository;


    @Override
    public void updatePost(Board board) throws Exception {

    }

    @Override
    public void deletePost(long postNo) throws Exception {

    }

    @Override
    public void writePost(Board board) throws Exception {

    }

    @Override
    public void updateLike(Liked liked) throws Exception {

    }

    @Override
    public void deleteLike(long likedNo) throws Exception {

    }

    @Override
    public void updateReply(Reply reply) throws Exception {

    }

    @Override
    public void deleteReply(long replyNo) throws Exception {

    }
}
