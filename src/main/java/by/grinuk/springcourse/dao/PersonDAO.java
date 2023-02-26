package by.grinuk.springcourse.dao;

import by.grinuk.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int ID;

    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(ID,"Tom"));
        ID++;
        people.add(new Person(ID,"Jack"));
        ID++;
        people.add(new Person(ID,"Bob"));
        ID++;
    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person){
        person.setId(++ID);
        people.add(person);
    }

    public void update(int id,Person updatedPerson){
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatedPerson.getName());
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }
}
