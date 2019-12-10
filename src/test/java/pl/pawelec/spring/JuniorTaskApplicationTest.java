package pl.pawelec.spring;

import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pawelec.spring.RunApplication;
import pl.pawelec.spring.model.Child;
import pl.pawelec.spring.model.Family;
import pl.pawelec.spring.model.Father;
import pl.pawelec.spring.model.PersonData;
import pl.pawelec.spring.repository.ChildRepository;
import pl.pawelec.spring.repository.FamilyRepository;
import pl.pawelec.spring.repository.FatherRepository;
import pl.pawelec.spring.repository.PersonDataRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RunApplication.class})
public class JuniorTaskApplicationTest {
    @Autowired
    private PersonDataRepository personDataRepository;
        
    @Autowired
    private FatherRepository fatherRepository;
        
    @Autowired
    private FamilyRepository familyRepository;
    
    @Autowired
    private ChildRepository childRepository;
    
    @Test
    public void testCreateMethod() {
        System.out.println(personDataRepository);
        System.out.println(fatherRepository);
        System.out.println(familyRepository);
        System.out.println(childRepository);
//        PersonData personData = personDataRepository.create(new PersonData("", "", ""));
//        Child child = childRepository.create(new Child(new PersonData(), "female", familyRepository.get(1l)));
//        assertNotNull(child);
//        System.out.println( "created: " + child );
//        System.out.println( "get row: " + childRepository.get(5L) );
//        System.out.println( "all: " + childRepository.getAll() );
//        System.out.println( "delete: " + familyRepository.delete(family) );
//        System.out.println( "delete person: " + personDataRepository.delete(personData) );
    }

}
