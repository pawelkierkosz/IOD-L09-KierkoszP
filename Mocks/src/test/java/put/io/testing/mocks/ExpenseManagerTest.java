package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalMatchers.and;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.service.FancyService;

public class ExpenseManagerTest {
    @Test
    void testCalculateTotal() {
        ExpenseRepository mock = mock(ExpenseRepository.class);
        FancyService fancyService = mock(FancyService.class);

        ArrayList<Expense> expenses = new ArrayList<Expense>();

        for (int i = 0; i < 3; i++){
            Expense e = new Expense();
            e.setAmount(333);
            expenses.add(e);
        }
        when(mock.getExpenses()).thenReturn(expenses);

        ExpenseManager expenseManager = new ExpenseManager(mock,fancyService);
        assertEquals(999, expenseManager.calculateTotal());
    }

    @Test
    public void testCalculateTotalForCategory() {
        ExpenseRepository mock = mock(ExpenseRepository.class);
        FancyService fancyService = mock(FancyService.class);

        ArrayList<Expense> home = new ArrayList<Expense>();
        ArrayList<Expense> car = new ArrayList<Expense>();

        for(int i = 0; i < 3; i++){
            Expense e1 = new Expense();
            e1.setCategory("Home");
            e1.setAmount(400);
            Expense e2 = new Expense();
            e2.setCategory("Car");
            e2.setAmount(250);
            home.add(e1);
            car.add(e2);
        }
        when(mock.getExpensesByCategory(and(not(eq("Home")), not(eq("Car"))))).thenReturn(Collections.emptyList());

        when(mock.getExpensesByCategory(eq("Car"))).thenReturn(car);
        when(mock.getExpensesByCategory(eq("Home"))).thenReturn(home);

        ExpenseManager expenseManager = new ExpenseManager(mock, fancyService);
        assertEquals(750, expenseManager.calculateTotalForCategory("Car"));
        assertEquals(1200, expenseManager.calculateTotalForCategory("Home"));
        assertEquals(0, expenseManager.calculateTotalForCategory("Food"));
        assertEquals(0, expenseManager.calculateTotalForCategory("Sport"));
    }

    @Test
    public void testCalculateTotalInDollars() throws ConnectException {
        ExpenseRepository repositoryMock = mock(ExpenseRepository.class);
        FancyService serviceMock = mock(FancyService.class);

        ExpenseManager expenseManager = new ExpenseManager(repositoryMock, serviceMock);

        when(serviceMock.convert(eq(0.0), eq("PLN"), eq("USD"))).thenThrow(new ConnectException());

        assertEquals(-1, expenseManager.calculateTotalInDollars());

        List<Expense> expenses = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Expense singleExpense = new Expense();
            singleExpense.setAmount(300);
            expenses.add(singleExpense);
        }

        when(repositoryMock.getExpenses()).thenReturn(expenses);

        when(serviceMock.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer(invocation -> {
            double amount = (double) invocation.getArguments()[0];
            return amount * 0.25;
        });

        assertEquals(300, expenseManager.calculateTotalInDollars(), 0.001);
    }

}
