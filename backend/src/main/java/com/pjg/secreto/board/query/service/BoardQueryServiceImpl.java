package com.pjg.secreto.board.query.service;

import com.pjg.secreto.board.command.repository.BoardCommandRepository;
import com.pjg.secreto.board.common.entity.Board;
import com.pjg.secreto.board.common.entity.Reply;
import com.pjg.secreto.board.query.repository.BoardQueryDSLRepository;
import com.pjg.secreto.board.query.repository.BoardQueryRepository;

import java.util.List;

public class BoardQueryServiceImpl implements BoardQueryService{
    private BoardQueryRepository boardQueryRepository;
    private BoardQueryDSLRepository boardQueryDSLRepository;
    private BoardCommandRepository boardCommandRepository;

    @Override
    public List<Board> getBoard() throws Exception {
        return null;
    }

    @Override
    public Board getPost() throws Exception {
        return null;
    }

    @Override
    public List<Reply> getRely() throws Exception {
        return null;
    }
}
