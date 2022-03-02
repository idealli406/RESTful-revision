package demo4.demo.service;

import demo4.demo.Employee;
import demo4.demo.Repository.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class EmployeeService  {
   @Autowired
    RestTemplate restTemplate;
    @Autowired
   EmployeeDao employeeDao;



    public Employee getAll() {
        String uri = "http://dummy.restapiexample.com/api/v1/employees";
        Employee result  = restTemplate.getForObject(uri, Employee.class);
        return result ;

    }

    public Employee getById(int id) {
        String uri = "http://dummy.restapiexample.com/api/v1/employees/"+id;
        Employee em= restTemplate.getForObject(uri, Employee.class);
        return em;
    }

    public void deleteEmployee(int id) {
        String uri = "http://dummy.restapiexample.com/api/v1/employees/"+id;
        Employee em= restTemplate.getForObject(uri, Employee.class);

    }

    public void addEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    public List<Employee> getByAge(int age) {
        String uri = "http://dummy.restapiexample.com/api/v1/employees";
      List<Employee> result  = (List<Employee>) restTemplate.getForObject(uri, Employee.class);

        return result.stream().filter(c->c.getEmployee_age()>age).collect(Collectors.toList());

    }


    public void save(Employee employees)
         {employeeDao.save (employees);}

}
