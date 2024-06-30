package sweet.dh.apiserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sweet.dh.apiserver.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
