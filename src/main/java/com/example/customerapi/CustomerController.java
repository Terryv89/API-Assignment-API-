package com.example.customerapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    CustomerList customerList;

    @GetMapping("/")
    public Customer getCustomer(String name) {
        return customerList.getCustomerByName(name);
    }

    @GetMapping("/greet")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/customerlist")
    public Customer[] getAllCustomersWithArray() {
        return customerList.getAllCustomers();
    }

    @PostMapping("/")
    public String addCustomer(Customer customer) {
        customerList.addCustomer(customer);
        return "Success";
    }

    @PutMapping("/")
    public ResponseEntity<String> changeMemberStatus(String name, String changeMemberShip) {
        boolean success = customerList.changeMemberStatus(name, changeMemberShip);
        if (success) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Success!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error when trying to change membership");
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteCustomer(String name) {
        boolean success = customerList.deleteCustomer(name);
        if (success) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Success deleting customer");

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No customer with that name to delete");
    }


}