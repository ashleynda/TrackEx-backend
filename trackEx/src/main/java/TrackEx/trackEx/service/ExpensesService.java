package TrackEx.trackEx.service;

import TrackEx.trackEx.data.model.Expenses;
import TrackEx.trackEx.data.model.User;
import TrackEx.trackEx.dtos.request.AddExpensesRequest;
import TrackEx.trackEx.dtos.response.AddExpensesResponse;

import java.util.List;

public interface ExpensesService {
    AddExpensesResponse addExpenses(AddExpensesRequest addExpensesRequest, User user);

    List<Expenses> getUserExpenses(User user);
}
