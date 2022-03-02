package demo4.demo.Repository;

import demo4.demo.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDao {
    private List<Employee> em= new ArrayList<>();


    public Employee get(int id) {
        return  em.get(id);
    }

    public List<Employee> getAll() {
        return em;
    }

    public void save(Employee employee) {
        em.add(employee);
    }

    public void delete(int id) {
        em.remove(id);

    }
}
