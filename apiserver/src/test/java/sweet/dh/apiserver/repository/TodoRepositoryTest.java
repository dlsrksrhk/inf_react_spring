package sweet.dh.apiserver.repository;

import jakarta.persistence.EntityManager;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sweet.dh.apiserver.domain.Todo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
@Transactional
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void test() {
        assertNotNull(todoRepository);
        log.info(todoRepository.getClass().getName());
    }

    @Test
    public void testInsert() {
        Todo todo = Todo.builder()
                .title("Test Title")
                .content("Test Content")
                .complete(false)
                .dueDate(LocalDate.now())
                .build();

        todoRepository.save(todo);
        assertNotNull(todo.getId());
        log.info("Saved Todo: {}", todo.getId());
    }

    @Test
    public void testRead() {
        Todo todo = todoRepository.findById(1L).orElse(null);

        assertNotNull(todo);
        assertEquals("Test Title", todo.getTitle());
        assertEquals("Test Content", todo.getContent());
        assertFalse(todo.isComplete());
        assertNotNull(todo.getDueDate());
        log.info("Retrieved Todo: {}", todo);
    }

    @Test
    public void testUpdate() {
        Todo todo = todoRepository.findById(1L).orElseThrow();
        todo.changeComplete(true);
        todo.changeTitle("Test Title2");
        todo.changeContent("Test Content2");
        log.info("Todo: {}", todo);

        em.flush();

        Todo findedTodo = todoRepository.findById(1L).orElseThrow();
        assertEquals("Test Title2", findedTodo.getTitle());
        assertEquals("Test Content2", findedTodo.getContent());
        log.info("findedTodo Todo: {}", todo);
    }
}