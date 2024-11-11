package com.example.demo.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DemoController {
//    public ContactDetails contactDetails;
    private final List<Contact> contacts = new ArrayList<>();



    @GetMapping("/greet")
    public String greet(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "hello " + name + "!" ;
    }


    @GetMapping("/")
    String hello2(@RequestParam String name) {
        return "hello " + name + "!" ;
    }

    @GetMapping("/goodbye/{id}/{age}")
    String goodbye(@PathVariable  Integer id, @PathVariable  Integer age) {
        return "goodbye " + id + age + " see you soon!";
    }
    @PostMapping("/farewell")
    String farewell (@RequestBody Farewell farewell) {
        return "Goodbye, " + farewell.name + "!";
    }

    @PostMapping("/Goodbye")
    public String Person (@RequestBody Person person) {
        System.out.println(person.getFirstName() );
        return "Goodbye, " + person.getFirstName() + "!";
    }




    //----------------------Task: Check your understanding with Rest API---------------------//

    @PostMapping("/addContact")
    public String addContact(@RequestBody Contact contact) {
        // Check for duplicate email
        boolean emailExists = contacts.stream()
                .anyMatch(existingContact -> existingContact.getEmail().equals(contact.getEmail()));

        if (emailExists) {
            return "A contact with this email already exists.";
        } else {
            contacts.add(contact);
            return "Contact " + contact.getName() + " added successfully!";
        }
    }

    @GetMapping ("/getContactDetails")
    String recieve (@RequestParam String name){
        for(Contact contact : contacts){
            if(contact.getName().equals(name)){
                return contact.toString();
            }
        }
        return "Contact not found.";
    }
}