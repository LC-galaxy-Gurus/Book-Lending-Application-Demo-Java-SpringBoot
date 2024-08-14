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
        CustomerModel customer1 = new CustomerModel();
        customer1.setEmailId("email1@example.com");
        CustomerModel customer2 = new CustomerModel();
        customer2.setEmailId("email2@example.com");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        // In a real test, avoid System.out.println and use proper logging or assertions
        assertEquals(2, customerService.getAllCustomers().size());
    }

    @Test
    void testGetCustomer() {
        CustomerModel customer = new CustomerModel();
        customer.setFirstName("John");
    
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
    
        CustomerModel result = customerService.getCustomer(1);
    
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

    @Test
    void testSaveCustomer() {
        CustomerModel customer = new CustomerModel();
        customer.setFirstName("Jane");

        when(customerRepository.save(customer)).thenReturn(customer);

        CustomerModel result = customerService.saveCustomer(customer);

        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
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
        // No need to stub if the method does not return a value or is not used.
        doNothing().when(customerRepository).deleteById(1);

        assertDoesNotThrow(() -> customerService.deleteCustomer(1));
    }


    @Test
    void testDeleteCustomerWithInvalidId() {
        assertDoesNotThrow(() -> customerService.deleteCustomer(0)); // Invalid ID
    }

}