package com.example.customerapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.customerapi.exception.ResourceNotFoundException;
import com.example.customerapi.model.CustomerDB;
import com.example.customerapi.repository.CustomerDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:5332")
@RestController
@RequestMapping("/api/v1/")
public class CustomerDBController {

    @Autowired
    CustomerDBRepository customerDBRepository;

    @GetMapping("/customerdb")
    public List<CustomerDB> getAllCustomers(){
        return customerDBRepository.findAll();
    }

    @PostMapping("/customerdb")
    public CustomerDB createCustomer(@RequestBody CustomerDB customerDB) {
        return customerDBRepository.save(customerDB);
    }

    @GetMapping("/customerdb/{id}")
    public ResponseEntity<CustomerDB> getCustomerById(@PathVariable Long id) {
        CustomerDB employee = customerDBRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id :" + id));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/customerdb/{id}")
    public ResponseEntity<CustomerDB> updateCustomer(@PathVariable Long id, @RequestBody CustomerDB employeeDetails){
        CustomerDB customerDB = customerDBRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id :" + id));

        customerDB.setName(employeeDetails.getName());
        customerDB.setLastName(employeeDetails.getLastName());
        customerDB.setEmailId(employeeDetails.getEmailId());

        CustomerDB updatedCustomer = customerDBRepository.save(customerDB);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/customerdb/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable Long id){
        CustomerDB customerDB = customerDBRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id :" + id));

        customerDBRepository.delete(customerDB);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}