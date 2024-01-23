package com.pjg.secreto.board.command.service;

import com.pjg.secreto.board.common.entity.Board;
import com.pjg.secreto.board.common.entity.Liked;
import com.pjg.secreto.board.common.entity.Reply;

public interface BoardCommandService {
    public void updatePost(Board board) throws Exception;

    public void deletePost(long postNo) throws Exception;

    public void writePost(Board board) throws Exception;

    public void updateLike(Liked liked) throws Exception;

    public void deleteLike(long likedNo) throws Exception;

    public void updateReply(Reply reply) throws Exception;

    public void deleteReply(long replyNo) throws Exception;
}
