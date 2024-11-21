package CodedBTA.AccountService.service;

import CodedBTA.AccountService.entity.ResponseEntity;
import CodedBTA.AccountService.repository.ResponseRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final ResponseRepository repository;

    public AccountServiceImpl(ResponseRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity userExists(ResponseEntity request) {
        return request;
    }
}
