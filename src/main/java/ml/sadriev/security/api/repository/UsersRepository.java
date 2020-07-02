package ml.sadriev.security.api.repository;

import ml.sadriev.security.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, String> {

    Users findUserByNickName(String nickName);
}
