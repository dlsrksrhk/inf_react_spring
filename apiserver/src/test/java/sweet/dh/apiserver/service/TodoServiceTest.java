package sweet.dh.apiserver.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sweet.dh.apiserver.domain.Todo;
import sweet.dh.apiserver.dto.TodoDTO;
import sweet.dh.apiserver.repository.TodoRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
@Transactional
public class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;

    public void insertTodos() {
        //make 100 Todos
        for (int i = 0; i < 100; i++) {
            Todo todo = Todo.builder()
                    .title("Test Title " + i)
                    .content("Test Content " + i)
                    .complete(i % 2 == 0)
                    .dueDate(LocalDate.now().plusDays(i))
                    .build();
            todoRepository.save(todo);
        }
    }

    @Test
    public void testGet() {
        insertTodos();
        Long tno = 50L;
        TodoDTO todoDTO = todoService.get(tno);
        log.info(todoDTO);

        assertEquals(todoDTO.getTno(),tno);
    }

    @Test
    public void register() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("test")
                .content("test content")
                .dueDate(LocalDate.now())
                .complete(true)
                .build();
        Long register = todoService.register(todoDTO);
        log.info(register);

        assertNotNull(register);

    }

}