package io.zipcoder.persistenceapp.Service;

import io.zipcoder.persistenceapp.Domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


//where the methods go
@Service
public class PersonService {

//    private SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
    /**
     * SQL methods needed:
     * Add - should work
     * Update - should work
     * Remove - works
     * Remove a list of people - in progress
     * Find single person by ID - works
     * Generate a map of lastnames to list people with that lastname - in progress
     * Generate a map of firstnames to the number of occurrences. - in progress
     */

    private static final String getAllPersons = "SELECT * FROM PERSON";
    private static final String getSingleId = "SELECT * FROM PERSON WHERE ID = ?";
    private static final String deletePerson = "DELETE * FROM PERSON WHERE ID = ?";
    private static final String findByLastName = "SELECT * FROM PERSON WHERE LAST_NAME = ?";
//    private static final String getMapLastName = "";
//    private static final String getMapFirstName = "";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void getSinglePersonById(Long id){
        this.jdbcTemplate.execute(getSingleId);
    }

    public void getAllPersons(){
        this.jdbcTemplate.queryForList(getAllPersons);
    }

    public void deletePersonById(Long id){
        this.jdbcTemplate.execute(deletePerson);
    }
    public void findPersonByLastName(String last_name){
        this.jdbcTemplate.execute(findByLastName);
    }
    public void addPerson(Person person) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "INSERT INTO person ( LAST_NAME, FIRST_NAME, MOBILE, BIRTHDAY, HOME_ID ) VALUES ('" + person.getLastName() +
                "','" + person.getFirstName() + "','" + person.getMobileNumber() + "','" + format.format(person.getBirthDate()) + "','" + person.getHomeId() + "')";
        jdbcTemplate.execute(sql);
    }
    public void updatePerson(Person person, Long id) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "UPDATE person SET FIRST_NAME = '" + person.getFirstName() +"', LAST_NAME = '" + person.getLastName() +
                "', MOBILE = '" + person.getMobileNumber() + "', BIRTHDAY = '" + format.format(person.getBirthDate()) + "', HOME_ID = '" +
                person.getHomeId() + "' WHERE ID = " + id;
        jdbcTemplate.execute(sql);
    }


}
