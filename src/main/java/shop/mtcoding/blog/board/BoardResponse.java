package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.user.User;

import java.util.ArrayList;
import java.util.List;

public class BoardResponse {

    @Data
    public static class DTO {
        private int id;
        private String title;
        private String content;

        public DTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
        }
    }

    @Data
    public static class MainDTO{
        private int id;
        private String title;

        public MainDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }

    @Data
    public static class DetailDTO{
        private int  id;
        private String title;
        private String content;
        private int userId;
        private String username;
        private List<ReplyDTO> replies = new ArrayList<>();
        private boolean isOwner;

        public DetailDTO(Board board, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
//            this.replies = replyList.stream().map(reply -> new ReplyDTO(reply, sessionUser)).toList();
            this.replies = board.getReplies().stream().map(reply -> new ReplyDTO(reply, sessionUser)).toList(); // 한방 쿼리
            this.isOwner = false;
            if(sessionUser != null){
                if(sessionUser.getId() == userId) isOwner = true;
            }
        }

        @Data
        public class ReplyDTO{
            private int id;
            private String comment;
            private int userId;
            private String username;
            private boolean isOwner;

            public ReplyDTO(Reply reply, User sessionUser) {
                this.id = reply.getId();
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();
                this.username = reply.getUser().getUsername();
                this.isOwner = false;
                if(sessionUser != null){
                    if(sessionUser.getId() == userId) isOwner = true;
                }
            }

        }
    }

    @Data
    public static class CountDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private Long replyCount;

        public CountDTO(Integer id, String title, String content, Integer userId, Long replyCount) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.userId = userId;
            this.replyCount = replyCount;
        }
    }
}
