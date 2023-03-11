
package com.example.employee.controller;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.*;
 import java.util.*;

 import com.example.employee.service.*;
import com.example.employee.model.*;
 @RestController
 public class EmployeeController{
    @Autowired
    public EmployeeH2Service employeeService;

   @GetMapping("/employees")
   public ArrayList<Employee> getAllEmployees(){
      return employeeService.getAllEmployees();
   }

   @GetMapping("/employees/{employeeId}")
   public Employee getEmployee(@PathVariable int employeeId){
      return employeeService.getEmployee(employeeId);
   }

   @PostMapping("/employees")
   public Employee addEmployee(@RequestBody Employee employee){
      return employeeService.addEmployee(employee);
   }

   @PutMapping("/employees/{employeeId}")
   public Employee updateEmployee(@PathVariable int employeeId,@RequestBody Employee employee){
      return employeeService.updateEmployee(employeeId,employee);
   }

   @DeleteMapping("/employees/{employeeId}")
   public void deleteEmployee(@PathVariable int employeeId){
      employeeService.deleteEmployee(employeeId);
   }



 }
