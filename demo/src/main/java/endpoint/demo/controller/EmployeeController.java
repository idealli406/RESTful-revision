package endpoint.demo.controller;


import endpoint.demo.Exception.ResourceNotFoundException;
import endpoint.demo.model.employee;
import endpoint.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    //create get all employee api
    @GetMapping("/employees")
    public List<employee> getAllEmployees(){
       return  employeeRepository.findAll();
    }
    //create employee
    @PostMapping("/employees")
    public employee createEmployee(@Validated @RequestBody employee employee){
        return employeeRepository.save(employee);
    }
    //get employee by id
    @GetMapping("employees/{id}")
    public ResponseEntity<employee> getEmployeeByID(@PathVariable(value ="id") long id) throws ResourceNotFoundException {
     employee emp=employeeRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Employees Not Found for :+ id"));
     return ResponseEntity.ok().body(emp);
    }

    //update employee
    @PutMapping("/update/{id}")

    public ResponseEntity<employee> updatedEmployee(@PathVariable(value = "id") long id, @RequestBody employee employeeDetails)
            throws ResourceNotFoundException{


        employee emp=employeeRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Employees Not Found for :+ id"));
        emp.setEmployee_age(employeeDetails.getEmployee_age());
        emp.setEmployee_name(employeeDetails.getEmployee_name());
        emp.setEmployee_salary(employeeDetails.getEmployee_salary());
        emp.setProfile_image(employeeDetails.getProfile_image());
        employeeRepository.save(emp);
        return ResponseEntity.ok().body(emp);
    }
    //delete employee by id
   @DeleteMapping("/delete/{id}")
    public ResponseEntity DeleteEmployee(@PathVariable(value="id") long id) throws ResourceNotFoundException{
       employee emp=employeeRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Employees Not Found for :+ id"));
   employeeRepository.deleteById(id);
   return ResponseEntity.ok().build();

    }



}
