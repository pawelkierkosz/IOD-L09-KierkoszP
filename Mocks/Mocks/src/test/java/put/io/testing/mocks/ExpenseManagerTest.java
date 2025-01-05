package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalMatchers.*;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.database.IFancyDatabase;
import put.io.students.fancylibrary.service.FancyService;

public class ExpenseManagerTest {
    @Test
    void calculateTotalTest() {
        ExpenseRepository mockExpenseRepository = mock(ExpenseRepository.class);
        FancyService mockFancyService = mock(FancyService.class);

        List<Expense> expenses = new ArrayList<Expense>();

        for (int i=0; i<3; i++){
            Expense e = new Expense();
            e.setTitle("Title");
            e.setCategory("Category");
            e.setAmount(4);
            expenses.add(e);
        }

        when(mockExpenseRepository.getExpenses()).thenReturn(expenses);

        ExpenseManager manager = new ExpenseManager(mockExpenseRepository, mockFancyService);
        assertEquals(12, manager.calculateTotal());
    }

    @Test
    void calculateTotalForCategoryTest() {
        ExpenseRepository mockExpenseRepository = mock(ExpenseRepository.class);
        FancyService mockFancyService = mock(FancyService.class);

        List<Expense> expenses = new ArrayList<Expense>();

        for (int i=0; i<3; i++){
            Expense e = new Expense();
            e.setTitle("Title");
            e.setCategory("Category");
            e.setAmount(4);
            expenses.add(e);
        }

        when(mockExpenseRepository.getExpensesByCategory("Home")).thenReturn(expenses);
        when(mockExpenseRepository.getExpensesByCategory("Car")).thenReturn(expenses);
        when(mockExpenseRepository.getExpensesByCategory(and(not(eq("Home")), not(eq("Car"))))).thenReturn(Collections.emptyList());

        ExpenseManager manager = new ExpenseManager(mockExpenseRepository, mockFancyService);
        assertEquals(12, manager.calculateTotalForCategory("Home"));
        assertEquals(12, manager.calculateTotalForCategory("Car"));
        assertEquals(0, manager.calculateTotalForCategory("Other"));
    }

    @Test
    public void calculateTotalInDollarsTest() throws ConnectException {
        List <Expense> lista = new ArrayList<Expense>();
        for (int i = 0; i < 4; i++) {
            Expense expense = new Expense();
            expense.setAmount(2);
            lista.add(expense);
        }
        ExpenseRepository mockedRepository = mock(ExpenseRepository.class);
        FancyService mockedService = mock(FancyService.class);
        ExpenseManager manager = new ExpenseManager(mockedRepository, mockedService);
        when(mockedService.convert(eq(0.0),eq("PLN"),eq("USD"))).thenThrow(new ConnectException());
        assertEquals(-1, manager.calculateTotalInDollars());

        when(mockedRepository.getExpenses()).thenReturn(lista);
        when(mockedService.convert(anyDouble(),eq("PLN"),eq("USD"))).thenAnswer(new Answer(){
            public Object answer(InvocationOnMock onMock){
                return Double.parseDouble(onMock.getArguments()[0].toString())*0.25;

            }
        });
        assertEquals(2,manager.calculateTotalInDollars());
    }


}
