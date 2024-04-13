package shop.mtcoding.blog.board;

import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.reply.ReplyJPARepository;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJPARepository boardJPARepository;
    private final ReplyJPARepository replyJPARepository;

    public Board 글조회(int boardId){
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
        return board;
    }

    @Transactional
    public Board 글수정(int boardId, int sessionUserId, BoardRequest.UpdateDTO reqDTO){
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
        if(sessionUserId != board.getUser().getId()){
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }
        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());

        return board;
    }

    @Transactional
    public Board 글쓰기(BoardRequest.SaveDTO reqDTO, User sessionUser){
        Board board = boardJPARepository.save(reqDTO.toEntity(sessionUser));
        return board;
    }

    @Transactional
    public void 글삭제(int boardId, int sessionUserId){
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
        if(sessionUserId != board.getUser().getId()){
            throw  new Exception403("게시글을 삭제할 권한이 없습니다.");
        }
        boardJPARepository.deleteById(boardId);
    }

    @Transactional
    public List<BoardResponse.MainDTO> 글목록조회(){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Board> boardList = boardJPARepository.findAll(sort);
        return boardList.stream().map(board -> new BoardResponse.MainDTO(board)).toList();
    }

    @Transactional
    public BoardResponse.DetailDTO 글상세보기(int boardId, User sessionUser){
//        // Board, User (bw)
//        Board board = boardJPARepository.findByIdJoinUser(boardId)
//                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
//
//        // List<Reply> + User (rw)
//        List<Reply> replyList = replyJPARepository.findByBoardIdJoinUser(boardId);
        Board board = boardJPARepository.findDetail(boardId); // 한방 쿼리
        return new BoardResponse.DetailDTO(board, sessionUser);
    }

}
