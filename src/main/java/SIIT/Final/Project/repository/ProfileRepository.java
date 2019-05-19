package SIIT.Final.Project.repository;

import SIIT.Final.Project.model.User;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<User, Integer> {
}
