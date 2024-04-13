package shop.mtcoding.blog.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyJPARepository extends JpaRepository<Reply, Integer> {

    @Query("select r from Reply r where r.board.id = :boardId")
    List<Reply> findByBoardId(@Param("boardId") int boardId);

    @Query("select r from Reply r join fetch r.user u where r.board.id = :boardId")
    List<Reply> findByBoardIdJoinUser(@Param("boardId") int boardId);

}
