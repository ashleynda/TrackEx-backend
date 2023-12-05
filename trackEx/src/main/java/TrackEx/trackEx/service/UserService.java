package TrackEx.trackEx.service;

import TrackEx.trackEx.data.model.Expenses;
import TrackEx.trackEx.dtos.request.*;
import TrackEx.trackEx.dtos.response.*;

import java.util.List;

public interface UserService {

    RegisterUserResponse register(RegisterUserRequest registerUserRequest);

    LoginUserResponse login(LoginUserRequest loginUserRequest);

    AddExpensesResponse addExpenses(AddExpensesRequest addExpensesRequest);

    AddIncomeResponse addIncome(AddIncomeRequest addIncomeRequest);

    SetSavingResponse setSavingGoal(SetSavingGoalRequest request);

    String getBalance(String id);

    UpdateUserIncomeResponse updateIncome(UpdateUserIncomeRequest updateUserRequest);

    List<Expenses> getExpenses(String id);

    String logout(String id);
}
