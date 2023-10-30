package com.springboot.cruddemo.rest;

import com.springboot.cruddemo.entity.Employee;
import com.springboot.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId){
        Employee employeeID = employeeService.findById(employeeId);

        if(employeeID == null){
            throw  new RuntimeException("Employee id not found- "+ employeeId);
        }
        return employeeID;
    }

    @PostMapping("employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0);

        Employee employee1 = employeeService.save(employee);

        return employee1;
    }

    @PutMapping("employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee employee1= employeeService.save(employee);

        return employee1;
    }

    @DeleteMapping("employees/{employeeId}")
    public String delete(@PathVariable int employeeId){
        Employee employee = employeeService.findById(employeeId);

        if(employee == null){
            throw new  RuntimeException("Employee Id not found "+ employeeId);
        }
        employeeService.deleteById(employeeId);

        return "Deleted Employee Id- "+ employeeId;
    }


}
