package demo4.demo.controller;

import demo4.demo.Employee;
import demo4.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController

public class EmployeeController  {

    private EmployeeService employeeService;
    private RestTemplate restTemplate;

    @Autowired
    public EmployeeController(EmployeeService employeeService, RestTemplate restTemplate ){

        this.employeeService=employeeService;
        this.restTemplate= restTemplate;

    }


    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value ="id")int id) {

    return new ResponseEntity<Employee>(employeeService.getById(id), HttpStatus.OK);

    }

    @GetMapping("/employees")
    public ResponseEntity<Employee> getEmployees() {

        return new ResponseEntity<Employee>(employeeService.getAll(), HttpStatus.OK );

    }
    @GetMapping("/employees/{age}")
   public ResponseEntity<List<Employee>> getByAge(@PathVariable(value="age") int age ){
        return  new ResponseEntity<List<Employee>>(employeeService.getByAge(age), HttpStatus.OK);
   }

   @DeleteMapping("/employees/{id}")
    public ResponseEntity deleteById(@PathVariable(value= "id") int id){
       employeeService.deleteEmployee(id);
       return ResponseEntity.ok().build();
   }

   @PostMapping("/emlpoyees")
    public ResponseEntity<Employee> createEmployee (@PathVariable (value ="id") int id, @RequestBody Employee employeeDetails){
       Employee e= employeeService.getById(id);
       e.setEmployee_age(employeeDetails.getEmployee_age());
       e.setEmployee_name(employeeDetails.getEmployee_name());
       e.setEmployee_salary(employeeDetails.getEmployee_salary());
       e.setId(employeeDetails.getId());
       employeeService.save(e);
       return ResponseEntity.ok().body(e);

   }






}
