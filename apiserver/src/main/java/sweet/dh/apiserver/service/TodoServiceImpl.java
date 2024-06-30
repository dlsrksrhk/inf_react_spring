package sweet.dh.apiserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sweet.dh.apiserver.domain.Todo;
import sweet.dh.apiserver.dto.TodoDTO;
import sweet.dh.apiserver.repository.TodoRepository;

import java.util.Optional;

@Transactional
@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public TodoDTO get(Long tno) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        return entityToDTO(todo);
    }

    @Override
    public Long register(TodoDTO dto) {
        Todo todo = dtoToEntity(dto);
        return todoRepository.save(todo).getTno();
    }

    @Override
    public void modify(TodoDTO dto) {
        Todo todo = todoRepository.findById(dto.getTno()).orElseThrow();
        todo.changeTitle(dto.getTitle());
        todo.changeContent(dto.getContent());
        todo.changeComplete(dto.getComplete());
        todo.changeDueDate(dto.getDueDate());
    }

    @Override
    public void remove(Long tno) {
        todoRepository.deleteById(tno);
    }
}
