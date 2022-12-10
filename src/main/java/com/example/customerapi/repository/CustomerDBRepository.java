package com.example.customerapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

import com.example.customerapi.model.CustomerDB;

@Repository
public interface CustomerDBRepository extends JpaRepository<CustomerDB, Long> {

}