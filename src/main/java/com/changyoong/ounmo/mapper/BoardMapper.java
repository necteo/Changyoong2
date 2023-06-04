package com.changyoong.ounmo.mapper;

import com.changyoong.ounmo.domain.board.Board;
import com.changyoong.ounmo.dto.board.BoardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BoardMapper {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    default BoardDTO toBoardDTO(Board board) {
        if (board == null)
            return null;

        List<String> filePaths = new ArrayList<>();
        board.getFiles().forEach(file -> filePaths.add(file.getFilePath()));

        return BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .filePaths(filePaths)
                .build();
    }

    default List<BoardDTO> toBoardDTOList(List<Board> boards) {
        List<BoardDTO> boardDTOList = new ArrayList<>();
        boards.forEach(board ->
                boardDTOList.add(BoardMapper.INSTANCE.toBoardDTO(board)));
        return boardDTOList;
    }
}
