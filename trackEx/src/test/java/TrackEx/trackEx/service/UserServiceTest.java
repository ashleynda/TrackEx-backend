package TrackEx.trackEx.service;

import TrackEx.trackEx.data.model.Expenses;
import TrackEx.trackEx.data.repository.UserRepository;
import TrackEx.trackEx.dtos.request.*;
import TrackEx.trackEx.dtos.response.*;
import TrackEx.trackEx.exceptions.UserAlreadyExist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp(){
        userRepository.deleteAll();
    }

    @Test
    public void testUserCanRegister(){

        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("email");
        registerUserRequest.setPassword("password");

        RegisterUserResponse registerUserResponse = userService.register(registerUserRequest);
        assertEquals(1, userRepository.count());
        assertNotNull(registerUserRequest);
    }

    @Test
    public void testThatUserCanNotRegisterTwice(){

        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("email");
        registerUserRequest.setPassword("password");

        userService.register(registerUserRequest);

        assertThrows(UserAlreadyExist.class,
                ()->userService.register(registerUserRequest));
        assertEquals(1, userRepository.count());

    }

    @Test
    public void testThatUserCanLogin(){

        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("email");
        registerUserRequest.setPassword("password");

        userService.register(registerUserRequest);
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail("email");
        loginUserRequest.setPassword("password");

        LoginUserResponse loginUserResponse = userService.login(loginUserRequest);
        assertThat(loginUserResponse.isLoggedIn(), is(true));

    }

    @Test
    public void testThatUserCanAddExpenses(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("email");
        registerUserRequest.setPassword("password");

        userService.register(registerUserRequest);
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail("email");
        loginUserRequest.setPassword("password");

        LoginUserResponse loginUserResponse = userService.login(loginUserRequest);
        assertThat(loginUserResponse.isLoggedIn(), is(true));

        AddExpensesRequest addExpensesRequest = new AddExpensesRequest();
        addExpensesRequest.setCategory("Home");
        addExpensesRequest.setDescription("House Rent");
        addExpensesRequest.setAccountOwnerId(loginUserResponse.getId());
        addExpensesRequest.setAmount("10000");


        AddExpensesResponse addExpensesResponse = userService.addExpenses(addExpensesRequest);
        assertNotNull(addExpensesResponse);
    }

    @Test
    public void testTestThatUserCanAddIncome(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("email");
        registerUserRequest.setPassword("password");

        userService.register(registerUserRequest);
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail("email");
        loginUserRequest.setPassword("password");

        LoginUserResponse loginUserResponse = userService.login(loginUserRequest);
        assertThat(loginUserResponse.isLoggedIn(), is(true));

        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setAmount("100000");
        addIncomeRequest.setAccountId(loginUserResponse.getId());

        AddIncomeResponse addIncomeResponse = userService.addIncome(addIncomeRequest);
        assertNotNull(addIncomeResponse);

    }

    @Test
    public void testThatUserCanSetSavingGoal(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("email");
        registerUserRequest.setPassword("password");

        userService.register(registerUserRequest);
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail("email");
        loginUserRequest.setPassword("password");

        LoginUserResponse loginUserResponse = userService.login(loginUserRequest);
        assertThat(loginUserResponse.isLoggedIn(), is(true));

        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setAmount("100000");
        addIncomeRequest.setAccountId(loginUserResponse.getId());

        AddIncomeResponse addIncomeResponse = userService.addIncome(addIncomeRequest);
        assertNotNull(addIncomeResponse);

        SetSavingGoalRequest request = new SetSavingGoalRequest();
        request.setAmount("10000");
        request.setAccountOwnerId(loginUserResponse.getId());

        SetSavingResponse setSavingResponse = userService.setSavingGoal(request);
        assertThat(setSavingResponse.getSavingGoal(), is("10000"));

    }

    @Test
    public void testThatUserCanGetBalance(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("email");
        registerUserRequest.setPassword("password");

        userService.register(registerUserRequest);
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail("email");
        loginUserRequest.setPassword("password");

        LoginUserResponse loginUserResponse = userService.login(loginUserRequest);
        assertThat(loginUserResponse.isLoggedIn(), is(true));

        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setAmount("100000");
        addIncomeRequest.setAccountId(loginUserResponse.getId());

        AddIncomeResponse addIncomeResponse = userService.addIncome(addIncomeRequest);
        assertNotNull(addIncomeResponse);

        AddExpensesRequest addExpensesRequest = new AddExpensesRequest();
        addExpensesRequest.setCategory("Home");
        addExpensesRequest.setDescription("House Rent");
        addExpensesRequest.setAccountOwnerId(loginUserResponse.getId());
        addExpensesRequest.setAmount("10000");

        AddExpensesResponse addExpensesResponse = userService.addExpenses(addExpensesRequest);
        assertNotNull(addExpensesResponse);


        SetSavingGoalRequest request = new SetSavingGoalRequest();
        request.setAmount("10000");
        request.setAccountOwnerId(loginUserResponse.getId());

        SetSavingResponse setSavingResponse = userService.setSavingGoal(request);
        assertThat(setSavingResponse.getSavingGoal(), is("10000"));

        String balance = userService.getBalance(loginUserResponse.getId());
        assertThat(balance, is("90000"));

    }

    @Test
    public void testThatUserCanUpdateIncome(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("email");
        registerUserRequest.setPassword("password");

        userService.register(registerUserRequest);
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail("email");
        loginUserRequest.setPassword("password");

        LoginUserResponse loginUserResponse = userService.login(loginUserRequest);
        assertThat(loginUserResponse.isLoggedIn(), is(true));

        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setAmount("100000");
        addIncomeRequest.setAccountId(loginUserResponse.getId());

        AddIncomeResponse addIncomeResponse = userService.addIncome(addIncomeRequest);
        assertNotNull(addIncomeResponse);

        UpdateUserIncomeRequest updateUserRequest = new UpdateUserIncomeRequest();
        updateUserRequest.setAccountOwnerId(loginUserResponse.getId());
        updateUserRequest.setAmount("12000");

        UpdateUserIncomeResponse userIncomeResponse = userService.updateIncome(updateUserRequest);
        assertThat(userIncomeResponse.getNewIncome(), is("112000"));
    }

    @Test
    public void testThatUserCanCheckExpenses(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("email");
        registerUserRequest.setPassword("password");

        userService.register(registerUserRequest);
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail("email");
        loginUserRequest.setPassword("password");

        LoginUserResponse loginUserResponse = userService.login(loginUserRequest);
        assertThat(loginUserResponse.isLoggedIn(), is(true));

        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setAmount("100000");
        addIncomeRequest.setAccountId(loginUserResponse.getId());

        AddIncomeResponse addIncomeResponse = userService.addIncome(addIncomeRequest);
        assertNotNull(addIncomeResponse);

        UpdateUserIncomeRequest updateUserRequest = new UpdateUserIncomeRequest();
        updateUserRequest.setAccountOwnerId(loginUserResponse.getId());
        updateUserRequest.setAmount("12000");

        UpdateUserIncomeResponse userIncomeResponse = userService.updateIncome(updateUserRequest);
        assertThat(userIncomeResponse.getNewIncome(), is("112000"));

        SetSavingGoalRequest request = new SetSavingGoalRequest();
        request.setAmount("10000");
        request.setAccountOwnerId(loginUserResponse.getId());

        SetSavingResponse setSavingResponse = userService.setSavingGoal(request);
        assertThat(setSavingResponse.getSavingGoal(), is("10000"));

        AddExpensesRequest addExpensesRequest = new AddExpensesRequest();
        addExpensesRequest.setCategory("Home");
        addExpensesRequest.setDescription("House Rent");
        addExpensesRequest.setAccountOwnerId(loginUserResponse.getId());
        addExpensesRequest.setAmount("10000");

        AddExpensesResponse addExpensesResponse = userService.addExpenses(addExpensesRequest);
        assertNotNull(addExpensesResponse);

        AddExpensesRequest expensesRequest = new AddExpensesRequest();
        expensesRequest.setCategory("Food Stuff");
        expensesRequest.setDescription("Money For Food");
        expensesRequest.setAccountOwnerId(loginUserResponse.getId());
        expensesRequest.setAmount("15000");

        AddExpensesResponse response = userService.addExpenses(expensesRequest);
        assertNotNull(response);

        List<Expenses> allExpenses = userService.getExpenses(loginUserResponse.getId());
        assertThat(allExpenses.size(), is(2));

    }



}