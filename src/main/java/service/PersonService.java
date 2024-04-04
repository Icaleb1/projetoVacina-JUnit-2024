package service;

import java.util.Objects;

import model.entity.Person;

public class PersonService {
	
	public boolean isAdult(Person person) {
		String message;
		Objects.requireNonNull(person, message = "Person can't be null");
		return person.getAge() >= 18;
		
	}

}
