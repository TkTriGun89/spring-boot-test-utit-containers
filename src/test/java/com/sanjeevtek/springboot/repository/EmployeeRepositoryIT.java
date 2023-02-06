package com.sanjeevtek.springboot.repository;

import com.sanjeevtek.springboot.model.Employee;
import com.sanjeevtek.springboot.integration.AbstractContainerBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryIT extends AbstractContainerBaseTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .firstName("Sanjeev")
                .lastName("Kumar S")
                .email("Sanjeev@gmail,com")
                .build();
    }
    // JUnit test for save employee operation
    //@DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Sanjeev")
                .lastName("Sanjeev")
                .email("Sanjeev@gmail,com")
                .build();
        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }


    // JUnit test for get all employees operation
    @DisplayName("JUnit test for get all employees operation")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Sanjeev")
//                .lastName("Sanjeev")
//                .email("Sanjeev@gmail,com")
//                .build();

        Employee employee1 = Employee.builder()
                .firstName("John")
                .lastName("Cena")
                .email("cena@gmail,com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        // when -  action or the behaviour that we are going test
        List<Employee> employeeList = employeeRepository.findAll();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }

    // JUnit test for get employee by id operation
    @DisplayName("JUnit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Sanjeev")
//                .lastName("Sanjeev")
//                .email("Sanjeev@gmail,com")
//                .build();
        employeeRepository.save(employee);

        // when -  action or the behaviour that we are going test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    // JUnit test for get employee by email operation
    @DisplayName("JUnit test for get employee by email operation")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Sanjeev")
//                .lastName("Kumar S")
//                .email("Sanjeev@gmail,com")
//                .build();
        employeeRepository.save(employee);

        // when -  action or the behaviour that we are going test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    // JUnit test for update employee operation
    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Sanjeev")
//                .lastName("Kumar S")
//                .email("Sanjeev@gmail,com")
//                .build();
        employeeRepository.save(employee);

        // when -  action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("san@gmail.com");
        savedEmployee.setFirstName("San");
        Employee updatedEmployee =  employeeRepository.save(savedEmployee);

        // then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("san@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("San");
    }

    // JUnit test for delete employee operation
    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Sanjeev")
//                .lastName("Kumar S")
//                .email("Sanjeev@gmail,com")
//                .build();
        employeeRepository.save(employee);

        // when -  action or the behaviour that we are going test
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        // then - verify the output
        assertThat(employeeOptional).isEmpty();
    }

    // JUnit test for custom query using JPQL with index
    @DisplayName("JUnit test for custom query using JPQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Sanjeev")
//                .lastName("Kumar S")
//                .email("Sanjeev@gmail,com")
//                .build();
        employeeRepository.save(employee);
        String firstName = "Sanjeev";
        String lastName = "Kumar S";

        // when -  action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByJPQL(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for custom query using JPQL with Named params
    @DisplayName("JUnit test for custom query using JPQL with Named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Sanjeev")
//                .lastName("Kumar S")
//                .email("Sanjeev@gmail,com")
//                .build();
        employeeRepository.save(employee);
        String firstName = "Sanjeev";
        String lastName = "Kumar S";

        // when -  action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByJPQLNamedParams(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for custom query using native SQL with index
    @DisplayName("JUnit test for custom query using native SQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Sanjeev")
//                .lastName("Kumar S")
//                .email("Sanjeev@gmail,com")
//                .build();
        employeeRepository.save(employee);
        // String firstName = "Sanjeev";
        // String lastName = "Kumar S";

        // when -  action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByNativeSQL(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for custom query using native SQL with named params
    @DisplayName("JUnit test for custom query using native SQL with named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Sanjeev")
//                .lastName("Kumar S")
//                .email("Sanjeev@gmail,com")
//                .build();
        employeeRepository.save(employee);
        // String firstName = "Sanjeev";
        // String lastName = "Kumar S";

        // when -  action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByNativeSQLNamed(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

}
