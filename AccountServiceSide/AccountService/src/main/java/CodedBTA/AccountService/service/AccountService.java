package CodedBTA.AccountService.service;

import CodedBTA.AccountService.entity.ResponseEntity;

public interface AccountService {
    ResponseEntity userExists(ResponseEntity request);
}
