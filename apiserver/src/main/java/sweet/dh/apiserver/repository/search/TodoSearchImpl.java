package sweet.dh.apiserver.repository.search;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import sweet.dh.apiserver.domain.QTodo;
import sweet.dh.apiserver.domain.Todo;

import java.util.List;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Page<Todo> search1() {
        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo)
                .where(todo.title.contains("title"));

        PageRequest pageable = PageRequest.of(1, 10, Sort.by("tno").descending());
        getQuerydsl().applyPagination(pageable, query);

        List<Todo> todos = query.fetch();
        long count = query.fetchCount();

        log.info(todos);
        log.info(count);
        return null;
    }
}
