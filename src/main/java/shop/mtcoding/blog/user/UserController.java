package shop.mtcoding.blog.user;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.utils.ApiUtil;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> userinfo(@PathVariable Integer id){
        UserResponse.DTO respDTO = userService.회원조회(id);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UserRequest.UpdateDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser newSessionUser = userService.회원수정(sessionUser.getId(), reqDTO);
        session.setAttribute("sessionUser", newSessionUser);
        UserResponse.DTO respDTO = userService.회원조회(newSessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO reqDTO) {
        UserResponse.DTO respDTO = userService.회원가입(reqDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO reqDTO) {
        String jwt = userService.로그인(reqDTO);
        return ResponseEntity.ok().header("Authorization", "Bearer "+jwt).body(new ApiUtil(null));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return ResponseEntity.ok(new ApiUtil(null));
    }
}
