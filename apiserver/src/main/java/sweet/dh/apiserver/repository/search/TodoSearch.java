package sweet.dh.apiserver.repository.search;

import org.springframework.data.domain.Page;
import sweet.dh.apiserver.domain.Todo;

public interface TodoSearch {
    public Page<Todo> search1();
}
