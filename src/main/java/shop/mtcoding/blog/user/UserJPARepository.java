package shop.mtcoding.blog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// 자동으로 Component Scan이 되어서 @Component가 필요 없다.
public interface UserJPARepository extends JpaRepository<User, Integer> {

    // findByUsernameAndPassword로 작성하면 jpa query method가 query creation을 발동시킨다.
    @Query("select u from User u where u.username = :username and u.password = :password")
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    Optional<User> findByUsername(@Param("username") String username);
}
