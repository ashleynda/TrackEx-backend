package TrackEx.trackEx.controller;

import TrackEx.trackEx.dtos.request.*;
import TrackEx.trackEx.dtos.response.ApiResponse;
import TrackEx.trackEx.dtos.response.UpdateUserIncomeResponse;
import TrackEx.trackEx.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/TrackEx")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public ApiResponse<Object> registerUser(@RequestBody RegisterUserRequest registerUserRequest ){
        try{
            return new ApiResponse<>(userService.register(registerUserRequest));
        }catch (Exception e){
            return new ApiResponse<>(e.getMessage());
        }
    }

    @PatchMapping("/login")
    public ApiResponse<Object> login(@RequestBody LoginUserRequest loginUserRequest){
        try{
            return new ApiResponse<>(userService.login(loginUserRequest));
        }catch (Exception e){
            return new ApiResponse<>(e.getMessage());
        }
    }

    @GetMapping("/check-balance/{id}")
    public ApiResponse<Object> checkBalance(@PathVariable String id){
        try{
            return new ApiResponse<>(userService.getBalance(id));
        }catch (Exception e){
            return new ApiResponse<>(e.getMessage());
        }
    }


    @GetMapping("/check-expenses/{id}")
    public ApiResponse<Object> checkExpenses(@PathVariable String id){
        try{
            return new ApiResponse<>(userService.getExpenses(id));
        }catch (Exception e){
            return new ApiResponse<>(e.getMessage());
        }
    }

    @PostMapping("/addIncome")
    public ApiResponse<Object> addIncome(@RequestBody AddIncomeRequest addIncomeRequest){
        try{
            return new ApiResponse<>(userService.addIncome(addIncomeRequest));
        }catch (Exception e){
            return new ApiResponse<>(e.getMessage());
        }
    }

    @PatchMapping("/setSavingGoal")
    public ApiResponse<Object> setSavingGoal(@RequestBody SetSavingGoalRequest setSavingGoalRequest){
        try{
            return new ApiResponse<>(userService.setSavingGoal(setSavingGoalRequest));
        }catch (Exception e){
            return new ApiResponse<>(e.getMessage());
        }
    }

    @PatchMapping("/updateIncome")
    public ApiResponse<Object> updateIncome(@RequestBody UpdateUserIncomeRequest request){
        try{
            return new ApiResponse<>(userService.updateIncome(request));
        }catch (Exception e){
            return new ApiResponse<>(e.getMessage());
        }
    }

    @PostMapping("/addExpenses")
    public ApiResponse<Object> addExpenses(@RequestBody AddExpensesRequest request){
        try{
            return new ApiResponse<>(userService.addExpenses(request));
        }catch (Exception e){
            return  new ApiResponse<>(e.getMessage());
        }
    }

    @PatchMapping("/logout")
    public String logout(@RequestBody String id){
        try{
            return userService.logout(id);
        }catch (Exception e){
            return e.getMessage();
        }
    }



}
