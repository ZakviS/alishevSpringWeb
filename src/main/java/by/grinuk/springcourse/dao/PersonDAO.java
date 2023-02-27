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

        people.add(new Person(ID,"Tom",25,"fdsfz@gmail.com"));
        ID++;
        people.add(new Person(ID,"Jack",11,"sdg@gmail.com"));
        ID++;
        people.add(new Person(ID,"Bob",15,"sadc@gmail.com"));
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

        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
        personToBeUpdated.setName(updatedPerson.getName());
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }
}
