package by.grinuk.springcourse.dao;

import by.grinuk.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/person";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "5v50a08V";

    private static Connection connection;

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Person> index()  {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return people;
    }

    public Person show(int id){
        Person person = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * From Person WHERE id=?");

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    return person;
    }

    public void save(Person person)  {
       try {
           PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person VALUES (1,?,?,?)");

           preparedStatement.setString(1, person.getName());
           preparedStatement.setInt(2, person.getAge());
           preparedStatement.setString(3, person.getEmail());

           preparedStatement.executeUpdate();
       }catch (SQLException throwables){
           throwables.printStackTrace();
       }
    }

  public void update(int id,Person updatedPerson){
      try {
          PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Person SET name=?, age=?,email=? WHERE id=?");

          preparedStatement.setString(1, updatedPerson.getName());
          preparedStatement.setInt(2, updatedPerson.getAge());
          preparedStatement.setString(3, updatedPerson.getEmail());
          preparedStatement.setInt(4,id);

          preparedStatement.executeUpdate();
      }catch (SQLException throwables){
          throwables.printStackTrace();
      }
    }

    public void delete(int id){
        PreparedStatement preparedStatement = null;
        try {
             preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id=?");

            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
