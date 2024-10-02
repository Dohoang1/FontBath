package dao;

import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO implements IPersonDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/font_bath_destroyer?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    private static final String INSERT_PERSONS_SQL = "INSERT INTO persons (name, gender, age, inscription, image) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_PERSON_BY_ID = "SELECT id, name, gender, age, inscription, image FROM persons WHERE id = ?";
    private static final String SELECT_ALL_PERSONS = "SELECT * FROM persons";
    private static final String UPDATE_PERSONS_SQL = "UPDATE persons SET name = ?, gender = ?, age = ?, inscription = ?, image = ? WHERE id = ?";
    private static final String DELETE_PERSONS_SQL = "DELETE FROM persons WHERE id = ?";
    private static final String SELECT_ALL_PERSONS_BY_NAME = "SELECT * FROM persons WHERE name LIKE ?";
    private static final String SELECT_ALL_PERSONS_BY_ID = "SELECT * FROM persons WHERE id LIKE ?";
    private static final String SORT_ALL_PERSONS_BY_NAME = "SELECT * FROM persons ORDER BY name";
    private static final String SORT_ALL_PERSONS_BY_ID = "SELECT * FROM persons ORDER BY id";

    public PersonDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertPerson(Person person) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERSONS_SQL)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getGender());
            preparedStatement.setInt(3, person.getAge());
            preparedStatement.setString(4, person.getInscription());
            preparedStatement.setString(5, person.getImage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Person selectPerson(int id) {
        Person person = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PERSON_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String inscription = rs.getString("inscription");
                String image = rs.getString("image");
                person = new Person(id, name, gender, age, inscription, image);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return person;
    }

    public List<Person> selectAllPersons() {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PERSONS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String inscription = rs.getString("inscription");
                String image = rs.getString("image");
                persons.add(new Person(id, name, gender, age, inscription, image));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return persons;
    }


    public List<Person> searchPersonsByName(String nameParam) throws SQLException {
        String sql = SELECT_ALL_PERSONS_BY_NAME;
        return executeSearch(sql, "%" + nameParam + "%");
    }

    public List<Person> searchPersonsById(String idParam) throws SQLException {
        String sql = SELECT_ALL_PERSONS_BY_ID;
        return executeSearch(sql, idParam);
    }

    private List<Person> executeSearch(String sql, String param) throws SQLException {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, param);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String gender = rs.getString("gender");
                    int age = rs.getInt("age");
                    String inscription = rs.getString("inscription");
                    String image = rs.getString("image");
                    persons.add(new Person(id, name, gender, age, inscription, image));
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
        return persons;
    }

    public List<Person> sortPersonByName(String order) throws SQLException {
        String sql = SORT_ALL_PERSONS_BY_NAME + (order.equalsIgnoreCase("desc") ? " DESC" : " ASC");
        return executeSort(sql);
    }

    public List<Person> sortPersonById(String order) throws SQLException {
        String sql = SORT_ALL_PERSONS_BY_ID + (order.equalsIgnoreCase("desc") ? " DESC" : " ASC");
        return executeSort(sql);
    }

    private List<Person> executeSort(String sql) throws SQLException {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String inscription = rs.getString("inscription");
                String image = rs.getString("image");
                persons.add(new Person(id, name, gender, age, inscription, image));
            }
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
        return persons;
    }

    @Override
    public boolean deletePerson(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PERSONS_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updatePerson(Person person) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PERSONS_SQL)) {
            statement.setString(1, person.getName());
            statement.setString(2, person.getGender());
            statement.setInt(3, person.getAge());
            statement.setString(4, person.getInscription());
            statement.setString(5, person.getImage());
            statement.setInt(6, person.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}