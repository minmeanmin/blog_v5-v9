package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.user.User;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BoardJPARepositoryTest {
    @Autowired
    private BoardJPARepository boardJPARepository;

    @Autowired
    private EntityManager em;

    @Test
    public void save_test(){
        // given
        User sessionUser = User.builder().id(1).build();
        Board board = Board.builder()
                .title("제목5")
                .content("내용5")
                .user(sessionUser)
                .build();

        // when
        boardJPARepository.save(board);

        // eye
        Optional<Board> boardOP = boardJPARepository.findById(board.getId());
        if(boardOP.isPresent()){
            Board board1 = boardOP.get();
            System.out.println(board1);
        }

        // then
        Assertions.assertThat("save_test : " + boardOP.get().getId()).isEqualTo(5);

    }

    @Test
    public void findById_test(){
        // given
        int id = 1;

        // when
        Optional<Board> boardOP = boardJPARepository.findById(id);

        // eye
        if(boardOP.isPresent()){
            Board board = boardOP.get();
            System.out.println("findById_test : " + board);
        }

        // then
        Assertions.assertThat(boardOP.get().getTitle()).isEqualTo("제목1");

    }

    @Test
    public void findByIdJoinUser_test(){
        // given
        int id = 1;

        // when
        Optional<Board> boardOP = boardJPARepository.findByIdJoinUser(id);


        // eye
        if(boardOP.isPresent()){
            Board board = boardOP.get();
            System.out.println("findByIdJoinUser_test : " + board);
        }

        // then
        Assertions.assertThat(boardOP.get().getUser().getUsername()).isEqualTo("ssar");

    }

    @Test
    public void findAll_test(){
        // given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        // when
        List<Board> boardList = boardJPARepository.findAll(sort);

        // eye
        System.out.println("findAll_test : " + boardList);

        // then
        Assertions.assertThat(boardList.getFirst().getId()).isEqualTo(4);

    }

    @Test
    public void deleteById_test(){
        // given
        int id = 1;

        // when
        boardJPARepository.deleteById(id);
        em.flush();

        // eye
        List<Board> boardList = boardJPARepository.findAll();
        System.out.println("deleteById_test : " + boardList);

        // then
        Assertions.assertThat(boardList.getFirst().getId()).isEqualTo(2);

    }

}
