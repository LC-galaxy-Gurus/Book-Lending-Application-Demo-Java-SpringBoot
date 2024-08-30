# GenAI Hackathon - For Tech Debt reduction using open source Gen AI to Improve Productivity
 
# Book Lending Information System

## Due Date
- **Aug 9 **

## Purpose
This application will be used as a MVP to demonstrate:
- Developing, coding, and testing micro-services for given requirements using Gen AI tool
- Implementing your frontend using SpringBoot MVC application to consume REST APIs, or optionally using a React application.
- Find and resolve Tech Debt using Gen AI

## Description
- Implement a Book Lending Information System using Spring Boot micro-services.
- The application should allow librarians to manage books, including reviewing the list of books, checking out books to customers, and looking up book availability.
- You may need to create projects like Eureka server, micro-services, micro-services client, and front-end app (MVC webapp or React app).
- Define entity classes for Customer, Book, and Transaction with persistence using JPA. 
- Use H2 in memory db to create a database named `LendingDB` with three tables: Customer, Book, and Transaction.

### Data Types
1. **Customer**: FCustomerId, FirstName, LastName, Address, Phone, EmailId
2. **Book**: BookId, Title, AuthorLastName, AuthorFirstName, Rating (1 to 5)
3. **Transaction**: TransactionId, CustomerId, BookId, TrxnDate, TrxnType (Check-in vs Check-out indicator)
