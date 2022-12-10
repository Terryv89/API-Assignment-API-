package com.example.customerapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Data
@AllArgsConstructor
public class CustomerList {

    ArrayList<Customer> customerArrayList = new ArrayList<>();

    public CustomerList() {
        customerArrayList.add(new Customer("James", "Bond", "31", "yes"));
        customerArrayList.add(new Customer("Hulk", "Hogan", "49", "no"));
        customerArrayList.add(new Customer("Connor", "Mcgregor", "35", "yes"));
    }

    public Customer getCustomerByName(String name) {

        for (Customer customer : customerArrayList) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    public Customer[] getAllCustomers() {
        Customer[] customerArray = new Customer[customerArrayList.size()];
        for (int i = 0; i < customerArray.length; i++) {
            customerArray[i] = customerArrayList.get(i);
        }
        return customerArray;
    }

    public void addCustomer(Customer customer) {
        customerArrayList.add(customer);
    }

    public boolean changeMemberStatus(String name, String changeMemberShip) {
        for (Customer customer : customerArrayList) {
            if (customer.getName().equals(name)) {
                customer.setClubMember((changeMemberShip));
                return true;
            }
        }
        return false;
    }

    public boolean deleteCustomer(String name) {
        Customer customerToDelete = null;
        for (Customer customer : customerArrayList) {
            if (customer.getName().equals(name)) {
                customerToDelete = customer;
            }
        }
        if (customerToDelete != null) {
            customerArrayList.remove(customerToDelete);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "CustomerList{" +
                "customerArrayList=" + customerArrayList +
                '}';
    }
}