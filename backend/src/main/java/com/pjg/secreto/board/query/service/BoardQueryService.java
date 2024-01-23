package com.pjg.secreto.board.query.service;

import com.pjg.secreto.board.common.entity.Board;
import com.pjg.secreto.board.common.entity.Reply;

import java.util.List;

public interface BoardQueryService {
    public List<Board> getBoard() throws Exception;;

    public Board getPost() throws Exception;

    public List<Reply> getRely() throws Exception;

}
