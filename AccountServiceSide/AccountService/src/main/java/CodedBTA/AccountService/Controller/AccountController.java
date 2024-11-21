package CodedBTA.AccountService.Controller;

import CodedBTA.AccountService.entity.CustomerEntity;
import CodedBTA.AccountService.entity.ResponseEntity;
import CodedBTA.AccountService.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

//
//    @PostMapping("/transfer")
//    public ResponseEntity printSomething(@RequestBody ResponseEntity responseEntity) {
//        ResponseEntity Response = accountService.userExists(responseEntity);
//        return Response;
//    }


    @GetMapping("/AccountBalance")
    @CircuitBreaker(name = "orderMS", fallbackMethod = "fallbackMethod"
    public String printSomething(@RequestBody CustomerEntity customerEntity) {


        ResponseEntity Response = accountService.userExists(responseEntity);
        return Response;
    }

}
