package TrackEx.trackEx.service;

import TrackEx.trackEx.data.model.Expenses;
import TrackEx.trackEx.data.model.User;
import TrackEx.trackEx.data.repository.UserRepository;
import TrackEx.trackEx.dtos.request.*;
import TrackEx.trackEx.dtos.response.*;
import TrackEx.trackEx.exceptions.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static TrackEx.trackEx.utils.Mapper.map;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private ExpensesService expensesService;

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        checkIfUserAlreadyExists(registerUserRequest.getEmail());
        User user = map(registerUserRequest);
        User registeredUser = userRepository.save(user);

        return map(registeredUser);
    }

    @Override
    public LoginUserResponse login(LoginUserRequest loginUserRequest) {
        User user = findUser(loginUserRequest);
        checkUserPassword(loginUserRequest, user);

        user.setLoggedIn(true);
        userRepository.save(user);

        return map(user.getId(), user);
    }

    @Override
    public AddExpensesResponse addExpenses(AddExpensesRequest addExpensesRequest) {
        User user = findUserById(addExpensesRequest.getAccountOwnerId());
        checkIfUserIsLogged(user);
        AddExpensesResponse response = expensesService.addExpenses(addExpensesRequest, user);
        BigDecimal newBalance = user.getIncome()
                .subtract(BigDecimal.valueOf(Long.parseLong(addExpensesRequest.getAmount())));
        response.setBalance(String.valueOf(newBalance));
        user.setBalance(newBalance);
        userRepository.save(user);
        return response;
    }

    @Override
    public AddIncomeResponse addIncome(AddIncomeRequest addIncomeRequest) {
        User user = findUserById(addIncomeRequest.getAccountId());
        checkIfUserIsLogged(user);
        user.setIncome(BigDecimal.valueOf(Long.parseLong(addIncomeRequest.getAmount())));
        user.setBalance(BigDecimal.valueOf(Long.parseLong(addIncomeRequest.getAmount())));
        userRepository.save(user);

        AddIncomeResponse addIncomeResponse = new AddIncomeResponse();
        addIncomeResponse.setId(user.getId());
        addIncomeResponse.setMessage("Income added Successfully");
        return addIncomeResponse;
    }

    @Override
    public SetSavingResponse setSavingGoal(SetSavingGoalRequest request) {
        User user = findUserById(request.getAccountOwnerId());
        if(user.getIncome().compareTo(BigDecimal.valueOf(Long.parseLong(request.getAmount()))) < 0){
            throw new InsufficientIncomeException("Income is not sufficient to set Saving");
        }
        user.setSavingGoal(BigDecimal.valueOf(Long.parseLong(request.getAmount())));
        userRepository.save(user);

        SetSavingResponse setSavingResponse = new SetSavingResponse();
        setSavingResponse.setMessage("Saving set Successfully");
        setSavingResponse.setId(user.getId());
        setSavingResponse.setSavingGoal(request.getAmount());
        return setSavingResponse;

    }

    @Override
    public String getBalance(String id) {
        User user = findUserById(id);
        return String.valueOf(user.getBalance());
    }

    @Override
    public UpdateUserIncomeResponse updateIncome(UpdateUserIncomeRequest updateUserRequest) {
        User user = findUserById(updateUserRequest.getAccountOwnerId());
        BigDecimal newIncome = user.getIncome().add(BigDecimal.valueOf(Long.parseLong(updateUserRequest.getAmount())));
        user.setIncome(newIncome);
        userRepository.save(user);

        UpdateUserIncomeResponse updateUserIncomeResponse = new UpdateUserIncomeResponse();
        updateUserIncomeResponse.setNewIncome(String.valueOf(newIncome));
        updateUserIncomeResponse.setMessage("Income updated successfully");
        updateUserIncomeResponse.setId(user.getId());

        return updateUserIncomeResponse;
    }

    @Override
    public List<Expenses> getExpenses(String id) {
        User user = findUserById(id);
        List<Expenses> allExpenses = expensesService.getUserExpenses(user);
        return allExpenses;
    }

    @Override
    public String logout(String id) {
        User user = findUserById(id);
        user.setLoggedIn(false);

        userRepository.save(user);
        return "Logged out successful";
    }

    private void checkIfUserIsLogged(User user) {
        if(!user.isLoggedIn()){
            throw new UnacceptedLoginException("User is not Logged in");
        }
    }

    private User findUserById(String accountOwnerId) {
        Optional<User> foundUser = userRepository.findById(accountOwnerId);
        if (foundUser.isEmpty()) throw new UserNotFoundException("User Not Found");
        return foundUser.get();
    }

    private static void checkUserPassword(LoginUserRequest loginUserRequest, User user) {
        if(!user.getPassword().equals(loginUserRequest.getPassword())){
            throw new IncorrectPasswordException("Incorrect Password");
        }
    }


    private User findUser(LoginUserRequest loginUserRequest) {
        Optional<User> foundUser = userRepository.findUserByEmail(loginUserRequest.getEmail());
        if (foundUser.isEmpty()) throw new UserNotFoundException("User Not Found");
        return foundUser.get();
    }

    private void checkIfUserAlreadyExists(String email) {
        Optional<User> foundUser = userRepository.findUserByEmail(email);
        if (foundUser.isPresent()) throw new UserAlreadyExist("User Already Exit");
    }



}
