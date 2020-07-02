package ml.sadriev.security.api.service;

import ml.sadriev.security.model.Users;

public interface UsersService {

    Users findUserByNickName(String nickName) throws Exception;
}
