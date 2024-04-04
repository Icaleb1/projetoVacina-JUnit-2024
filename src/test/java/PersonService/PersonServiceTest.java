package PersonService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.entity.Person;
import service.PersonService;

class PersonServiceTest {

	@Test
	void testIsAdult() {
		Person person = new Person(21);
		PersonService personService = new PersonService();
			
		assertTrue(personService.isAdult(person));
	}

}
