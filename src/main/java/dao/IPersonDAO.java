package dao;

import model.Person;

import java.sql.SQLException;
import java.util.List;

public interface IPersonDAO {
    public void insertPerson(Person person) throws SQLException;

    public Person selectPerson(int id);

    public List<Person> selectAllPersons();

    public boolean deletePerson(int id) throws SQLException;

    public boolean updatePerson(Person person) throws SQLException;
}