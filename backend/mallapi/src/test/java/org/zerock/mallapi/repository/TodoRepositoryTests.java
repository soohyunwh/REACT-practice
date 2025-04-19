package org.zerock.mallapi.repository;

import static org.mockito.ArgumentMatchers.isNull;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.domain.Todo;
import org.zerock.mallapi.repository.TodoRepository;


import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testInsert() {
        for (int i=1; i<=100; i++) {

            Todo todo = Todo.builder()
            .title("title..." + i)
            .dueDate(LocalDate.of(2025,04,04))
            .writer("수현")
            .build();

            todoRepository.save(todo);
        }
    }
    @Test
    public void testRead() {

        Long tno = 33L;

        java.util.Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        log.info(todo);
    }
    @Test
    public void testModify() {

        Long tno = 33L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();
        todo.changeTitle("Modified 33...");
        todo.changeComplete(true);
        todo.changeDueDate(LocalDate.of(2025,04,05));

        todoRepository.save(todo);
    }
}

