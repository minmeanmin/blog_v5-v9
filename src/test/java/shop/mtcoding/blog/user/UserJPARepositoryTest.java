package shop.mtcoding.blog.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class UserJPARepositoryTest {

    @Autowired
    public UserJPARepository userJPARepository;

    @Test
    public void save_test(){
        // given
        User user = User.builder()
                .username("happy")
                .password("1234")
                .email("happy@nate.com")
                .build();

        // when
        userJPARepository.save(user);

        // eye
        Optional<User> userOP = userJPARepository.findById(4);
        if(userOP.isPresent()){
            System.out.println("save_test : " + userOP.get());
        }

        // then
        Assertions.assertThat(userOP.get().getId()).isEqualTo(4);
    }


    @Test
    public void findById_test(){
        // given
        int id = 1;

        // when
        Optional<User> userOP = userJPARepository.findById(id);

        // eye
        if(userOP.isPresent()){
            User user = userOP.get();
            System.out.println("findById_test : " + user.getUsername());
        }

        // then
        Assertions.assertThat(userOP.get().getId()).isEqualTo(id);

    }

    @Test
    public void findByUsernameAndPassword_test(){
        // given
        String username = "ssar";
        String password = "1234";

        // when
        Optional<User> userOP = userJPARepository.findByUsernameAndPassword(username, password);

        // eye
        if(userOP.isPresent()){
            User user = userOP.get();
            System.out.println("findByUsernameAndPassword_test : " + user);
        }

        // then
        Assertions.assertThat(userOP.get().getId()).isEqualTo(1);

    }

    @Test
    public void findByUsername_test(){
        // given
        String username = "cos";

        // when
        Optional<User> userOP = userJPARepository.findByUsername(username);

        // eye
        if(userOP.isPresent()){
            User user = userOP.get();
            System.out.println("findByUsername_test : " + user);
        }

        // then
        Assertions.assertThat(userOP.get().getEmail()).isEqualTo("cos@nate.com");

    }
}
