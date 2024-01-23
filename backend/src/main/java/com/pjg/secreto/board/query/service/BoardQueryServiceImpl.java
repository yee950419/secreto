package com.pjg.secreto.board.query.service;

import com.pjg.secreto.board.command.repository.BoardCommandRepository;
import com.pjg.secreto.board.query.repository.BoardQueryDSLRepository;
import com.pjg.secreto.board.query.repository.BoardQueryRepository;

public class BoardQueryServiceImpl implements BoardQueryService{
    private BoardQueryRepository boardQueryRepository;
    private BoardQueryDSLRepository boardQueryDSLRepository;
    private BoardCommandRepository boardCommandRepository;
}
