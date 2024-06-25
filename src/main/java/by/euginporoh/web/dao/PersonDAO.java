package by.euginporoh.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import by.euginporoh.web.models.Person;

@Component
public class PersonDAO {
	private static int PEOPLE_COUNT = 0;
	
	private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "6693895zen";
	
	private static Connection connection;
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Person> index() {
		List<Person> people = new ArrayList<Person>();
		
		try {
			Statement statement = connection.createStatement();
			String SQL = "SELECT * FROM Person";
			ResultSet resultSet = statement.executeQuery(SQL);
			
			while(resultSet.next()) {
				Person person = new Person();
				
				person.setId(resultSet.getInt("id"));
				person.setName(resultSet.getString("name"));
				person.setAge(resultSet.getInt("age"));
				person.setEmail(resultSet.getString("email"));
				
				people.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return people;
	}
	
	public Person show(int id) {
		Person person = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Person WHERE id=?");
			
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			resultSet.next();
			
			person = new Person();
			person.setId(resultSet.getInt("id"));
			person.setName(resultSet.getString("name"));
			person.setAge(resultSet.getInt("age"));
			person.setEmail(resultSet.getString("email"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}
	
	public void save(Person person) {		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person VALUES(1, ?, ?, ?)");
			preparedStatement.setString(1, person.getName());
			preparedStatement.setInt(2, person.getAge());
			preparedStatement.setString(3, person.getEmail());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(int id, Person updatePerson) {
		try {
			PreparedStatement preparedStatement = 
					connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");
			preparedStatement.setString(1, updatePerson.getName());
			preparedStatement.setInt(2, updatePerson.getAge());
			preparedStatement.setString(3, updatePerson.getEmail());
			preparedStatement.setInt(4, id);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		try {
			PreparedStatement preparedStatement = 
					connection.prepareStatement("DELETE FROM Person WHERE id=?");
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
