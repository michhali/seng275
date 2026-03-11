package lab06;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

class TodoApplicationTest {

    private TodoApplication todoApp;
    private PersonService personServiceMock;
    private TodoService todoServiceMock;

    private final String userName = "SomeUser";
    private final Long userID = 1L;
    private final List<String> todos = List.of("Wake up", "Test the code", "Celebrate the victory!");

    @BeforeEach
    void setUp() {
        personServiceMock = mock(PersonService.class);
        todoServiceMock = mock(TodoService.class);

        todoApp = new TodoApplication(todoServiceMock, personServiceMock);
    }

    @Test
    void addTodo() {
        todoApp.addTodo(userID, "Wake up");


        verify(todoServiceMock).addTodo(any(), any());
    }

    @Test
    void retrieveTodos() {

        when(todoServiceMock.retrieveTodos(any())).thenReturn(todos);

        List<String> filtered = todoApp.retrieveTodos(userID, "Test");

        assertThat(filtered).containsExactly("Test the code");
    }

    @Test
    void completeAllWithNoTodos() {
        when(todoServiceMock.retrieveTodos(any())).thenReturn(List.of());

        todoApp.completeAllTodos(userID);


        verify(todoServiceMock, never()).completeTodo(anyString());
    }

    @Test
    void completeAllWithThreeTodos() {
        when(todoServiceMock.retrieveTodos(any())).thenReturn(todos);

        todoApp.completeAllTodos(userID);

        verify(todoServiceMock, times(3)).completeTodo(anyString());
    }
}