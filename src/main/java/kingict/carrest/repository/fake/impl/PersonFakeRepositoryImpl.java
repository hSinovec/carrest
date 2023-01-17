package kingict.carrest.repository.fake.impl;

import kingict.carrest.entity.Person;
import kingict.carrest.repository.fake.PersonFakeRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonFakeRepositoryImpl implements PersonFakeRepository {
    private static final List<Person> persons = new ArrayList<>();
    private static Long ID = 0L;

    @Override
    public void save(Person person) {
        person.setId(ID++);
        persons.add(person);
    }

    @Override
    public Optional<Person> get(Long personId) {
        return persons.stream()
                .filter(person -> person.getId().equals(personId)).findFirst();
    }

    @Override
    public List<Person> getAll() {
        return persons;
    }

    @Override
    public void delete(Long personId) {
        persons.removeIf(person -> person.getId().equals(personId));
    }
}
