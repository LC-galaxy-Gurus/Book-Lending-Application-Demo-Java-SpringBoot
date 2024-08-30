package com.example.assignment4;

import com.example.assignment4.controllers.CustomerController;
import com.example.assignment4.models.CustomerModel;
import com.example.assignment4.services.CustomerService;
import com.example.assignment4.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void testUpdateCustomer_success() {
        // Arrange
        int customerId = 1;
        CustomerModel customer = new CustomerModel();
        customer.setFirstName("John");
        customer.setLastName("Doe");

        when(customerService.saveCustomer(customer)).thenReturn(customer);

        // Act
        ResponseEntity<CustomerModel> response = customerController.updateCustomer(customerId, customer);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void testUpdateCustomer_invalidInput() {
        // Setup invalid input
        CustomerModel invalidCustomer = new CustomerModel();
        invalidCustomer.setFirstName(""); // Assuming this is invalid

        // Invoke the method
        ResponseEntity<?> response = customerController.updateCustomer(1, invalidCustomer);

        // Verify the result
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testAddCustomer_success() {
        // Arrange
        CustomerModel customer = new CustomerModel();
        customer.setFirstName("Jane");
        customer.setLastName("Doe");

        when(customerService.saveCustomer(customer)).thenReturn(customer);

        // Act
        ResponseEntity<CustomerModel> response = customerController.addCustomer(customer);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void testGetAllCustomers() {
        // Arrange
        CustomerModel customer1 = new CustomerModel();
        customer1.setEmailId("email1@example.com");
        CustomerModel customer2 = new CustomerModel();
        customer2.setEmailId("email2@example.com");
    
        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));
    
        // Act
        ResponseEntity<List<CustomerModel>> response = customerController.getAllCustomers();
        List<CustomerModel> result = response.getBody(); // Extract the body
    
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }
    

    @Test
    void testGetCustomer() {
        // Arrange
        int customerId = 1;
        CustomerModel customer = new CustomerModel();
        customer.setFirstName("John");
    
        // Mock the service to return the expected customer
        when(customerService.getCustomer(customerId)).thenReturn(customer);
    
        // Act
        ResponseEntity<CustomerModel> response = customerController.getCustomer(customerId);
        CustomerModel result = response.getBody(); // Extracting body from ResponseEntity
    
        // Assert
        assertNotNull(result, "The customer should not be null");
        assertEquals("John", result.getFirstName(), "The customer's first name should be John");
    }
    

    @Test
    void testDeleteCustomer() {
        // Arrange
        doNothing().when(customerService).deleteCustomer(1);

        // Act & Assert
        assertDoesNotThrow(() -> customerController.deleteCustomer(1));
    }

    @Test
    void testDeleteCustomerWithInvalidId() {
        // Act & Assert
        assertDoesNotThrow(() -> customerController.deleteCustomer(0)); // Invalid ID
    }
}

/*
package com.example.assignment4;

import com.example.assignment4.controllers.CustomerController;
import com.example.assignment4.models.CustomerModel;
import com.example.assignment4.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.assignment4.repositories.CustomerRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    // Your test methods will go here
    @Test
    void testUpdateCustomer_success() {
        // Arrange
        int customerId = 1;
        CustomerModel customer = new CustomerModel();
        customer.setFirstName("John");
        customer.setLastName("Doe");

        when(customerService.saveCustomer(customer)).thenReturn(customer);

        // Act
        ResponseEntity<CustomerModel> response = customerController.updateCustomer(customerId, customer);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void testUpdateCustomer_invalidInput() {
        // Setup invalid input
        CustomerModel invalidCustomer = new CustomerModel();
        invalidCustomer.setFirstName(""); // Assuming this is invalid

        // Invoke the method
        ResponseEntity<?> response = customerController.updateCustomer(1, invalidCustomer);

        // Verify the result
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    void testAddCustomer_success() {
        // Arrange
        CustomerModel customer = new CustomerModel();
        customer.setFirstName("Jane");
        customer.setLastName("Doe");

        when(customerService.saveCustomer(customer)).thenReturn(customer);

        // Act
        ResponseEntity<CustomerModel> response = customerController.addCustomer(customer);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void testGetAllCustomers() {
        // Arrange
        CustomerModel customer1 = new CustomerModel();
        customer1.setEmailId("email1@example.com");
        CustomerModel customer2 = new CustomerModel();
        customer2.setEmailId("email2@example.com");
    
        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));
    
        // Act
        ResponseEntity<List<CustomerModel>> response = customerController.getAllCustomers();
    
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }
    


    @Test
    void testGetCustomer() {
        // Arrange
        CustomerModel customer = new CustomerModel();
        customer.setFirstName("John");
    
        when(customerService.getCustomer(1)).thenReturn(customer);
    
        // Act
        ResponseEntity<CustomerModel> response = customerController.getCustomer(1);
    
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", response.getBody().getFirstName());
    }
    
    @Test
    void testSaveCustomerWithInvalidData() {
        CustomerModel customer = new CustomerModel();
        customer.setFirstName(""); // invalid data

        CustomerModel result = customerService.saveCustomer(customer);

        assertNull(result); // Expecting null due to invalid data
    }

    @Test
    void testDeleteCustomer() {
        // Arrange
        doNothing().when(customerService).deleteCustomer(1);

        // Act & Assert
        assertDoesNotThrow(() -> customerController.deleteCustomer(1));
    }



    @Test
    void testDeleteCustomerWithInvalidId() {
        assertDoesNotThrow(() -> customerService.deleteCustomer(0)); // Invalid ID
    }

}
     */