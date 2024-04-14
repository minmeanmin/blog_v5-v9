package shop.mtcoding.blog._core.utils;

import org.junit.jupiter.api.Test;
import shop.mtcoding.blog.user.User;

public class JwtUtilTest {

    @Test
    public void create_test(){
        // given
        User user = User.builder()
                .id(1)
                .username("ssar")
                .build();

        // when
        String jwt = JwtUtil.create(user);
        System.out.println(jwt);

        // then


    }

    @Test
    public void verify_test(){
        // given
        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJibG9nIiwiaWQiOjEsImV4cCI6MTcxMzEwMzk4NSwidXNlcm5hbWUiOiJzc2FyIn0.71iR2teWA6Jq_Q5jIDjNctdhq4vjw6CTyAzRhIxI1N7SzOnXNtF-mZjv_7koaLKAo09Qj_4k40bKL6nHR2eKYA";

        // when
        JwtUtil.verify(jwt);
    }
}
