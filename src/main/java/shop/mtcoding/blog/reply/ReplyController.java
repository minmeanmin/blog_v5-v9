package shop.mtcoding.blog.reply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;

    @PostMapping("/reply/save")
    public String save(ReplyRequest.SaveDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.댓글쓰기(reqDTO, sessionUser);
        return "redirect:/board/"+reqDTO.getBoardId();
    }

}
