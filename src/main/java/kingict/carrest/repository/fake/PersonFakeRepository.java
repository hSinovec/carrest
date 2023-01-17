package kingict.carrest.repository.fake;

import kingict.carrest.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonFakeRepository {
    void save(Person person);

    Optional<Person> get(Long personId);

    List<Person> getAll();

    void delete(Long personId);
}
