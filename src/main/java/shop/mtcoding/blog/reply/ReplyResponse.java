package shop.mtcoding.blog.reply;

import lombok.Data;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;

public class ReplyResponse {

    @Data
    public static class DTO{
        private Integer id;
        private String comment;
        private Integer userId;
        private Integer boardId;
        private Timestamp createdAt;

        public DTO(Reply reply, User user, Board board) {
            this.id = reply.getId();
            this.comment = reply.getComment();
            this.userId = user.getId();
            this.boardId = board.getId();
            this.createdAt = reply.getCreatedAt();
        }
    }
}