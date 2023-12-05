package TrackEx.trackEx.service;

import TrackEx.trackEx.data.model.Expenses;
import TrackEx.trackEx.data.model.User;
import TrackEx.trackEx.data.repository.ExpensesRepository;
import TrackEx.trackEx.dtos.request.AddExpensesRequest;
import TrackEx.trackEx.dtos.response.AddExpensesResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ExpensesServiceImpl implements ExpensesService{

    private ExpensesRepository expensesRepository;

    @Override
    public AddExpensesResponse addExpenses(AddExpensesRequest addExpensesRequest, User user) {

        Expenses expenses = new Expenses();
        expenses.setAmount(BigDecimal.valueOf(Long.parseLong(addExpensesRequest.getAmount())));
        expenses.setCategory(addExpensesRequest.getCategory());
        expenses.setDescription(addExpensesRequest.getDescription());
        expenses.setAccountOwner(user);
        expensesRepository.save(expenses);

        AddExpensesResponse addExpensesResponse = new AddExpensesResponse();
        addExpensesResponse.setMessage("Expenses Added Successfully");
        addExpensesResponse.setUserId(user.getId());
        return addExpensesResponse;
    }

    @Override
    public List<Expenses> getUserExpenses(User user) {
        List<Expenses> allExp = expensesRepository.findAllExpensesByAccountOwner(user);
        return allExp;
    }
}
