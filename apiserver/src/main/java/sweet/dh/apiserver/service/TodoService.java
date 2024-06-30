package sweet.dh.apiserver.service;

import sweet.dh.apiserver.domain.Todo;
import sweet.dh.apiserver.dto.TodoDTO;

public interface TodoService {
    public TodoDTO get(Long tno);

    public Long register(TodoDTO dto);

    public void modify(TodoDTO dto);

    public void remove(Long tno);

    public default TodoDTO entityToDTO(Todo todo) {
        return TodoDTO.builder()
                .tno(todo.getTno())
                .title(todo.getTitle())
                .content(todo.getContent())
                .complete(todo.isComplete())
                .dueDate(todo.getDueDate())
                .build();
    }

    public default Todo dtoToEntity(TodoDTO todoDTO) {
        return Todo.builder()
                .tno(todoDTO.getTno())
                .title(todoDTO.getTitle())
                .content(todoDTO.getContent())
                .complete(todoDTO.getComplete())
                .dueDate(todoDTO.getDueDate())
                .build();
    }
}
