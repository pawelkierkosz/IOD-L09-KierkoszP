package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

public class ExpenseRepositoryTest {

    @Test
    void loadExpensesEmpty() {
        ExpenseRepository expenseRepository = new ExpenseRepository(new MyDatabase());
        expenseRepository.loadExpenses();
        assertEquals(0,expenseRepository.getExpenses().size());
    }

    @Test
    void loadExpensesTest() {
        IFancyDatabase mockObj = mock(IFancyDatabase.class);
        when(mockObj.queryAll()).thenReturn(Collections.emptyList());

        ExpenseRepository expenseRepository = new ExpenseRepository(mockObj);
        expenseRepository.loadExpenses();
        InOrder inOrder = inOrder(mockObj);
        inOrder.verify(mockObj).connect();
        inOrder.verify(mockObj).queryAll();
        inOrder.verify(mockObj).close();

        assertEquals(0,expenseRepository.getExpenses().size());
    }

    @Test
    void saveExpensesTest1() {
        IFancyDatabase mockObj = mock(IFancyDatabase.class);
        when(mockObj.queryAll()).thenReturn(Collections.emptyList());

        ExpenseRepository expenseRepository = new ExpenseRepository(mockObj);

        expenseRepository.addExpense(new Expense());
        expenseRepository.saveExpenses();
        InOrder inOrder = inOrder(mockObj);
        inOrder.verify(mockObj).connect();
        inOrder.verify(mockObj).persist(any(Expense.class));
        inOrder.verify(mockObj).close();
        verify(mockObj,atLeastOnce()).persist(any(Expense.class));

        assertEquals(1,expenseRepository.getExpenses().size());
    }

    @Test
    void saveExpensesTest2() {
        IFancyDatabase mockObj = mock(IFancyDatabase.class);
        when(mockObj.queryAll()).thenReturn(Collections.emptyList());

        ExpenseRepository expenseRepository = new ExpenseRepository(mockObj);
        for (int i = 0; i < 5; i++) {
            expenseRepository.addExpense(new Expense());
        }
        expenseRepository.saveExpenses();

        verify(mockObj,times(5)).persist(any(Expense.class));
        assertEquals(5,expenseRepository.getExpenses().size());
    }
}
