package by.euginporoh.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import by.euginporoh.web.models.Person;

public interface PeopleRepository extends JpaRepository<Person, Integer>{

}
