package com.example.employee.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.employee.model.*;
import com.example.employee.repository.EmployeeRepository;

@Service
public class EmployeeH2Service implements EmployeeRepository{
    @Autowired
    public JdbcTemplate db;

    
    public ArrayList<Employee> getAllEmployees(){
      List<Employee> listEm = db.query("select * from EMPLOYEELIST", new EmployeeRowMapper());
      ArrayList<Employee> arrlist = new ArrayList<>(listEm);
      return arrlist;
   }

   public Employee getEmployee(int employeeId){
    try{
        Employee emp = db.queryForObject("select * from EMPLOYEELIST where employeeId=?", new EmployeeRowMapper(),employeeId);
        return emp;
    }
    catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
      

   }

   public Employee addEmployee(Employee employee){
      db.update("insert into EMPLOYEELIST(employeeName,email,department) values(?,?,?)",employee.getEmployeeName(),employee.getEmail(),employee.getDepartment());
      Employee emp1 = db.queryForObject("select * from EMPLOYEELIST where employeeId=?", new EmployeeRowMapper(),employee.getEmployeeId());
      return emp1;
   }

   public Employee updateEmployee(int employeeId,Employee employee){
     if(employee.getEmployeeName() != null){
         db.update("update EMPLOYEELIST set employeeName=? where employeeId=?",employee.getEmployeeName(), employeeId);
      }
      if(employee.getEmail() != null){
         db.update("update EMPLOYEELIST set email=? where employeeId=?",employee.getEmail(), employeeId);
      }
      if(employee.getDepartment() != null){
         db.update("update EMPLOYEELIST set department=? where employeeId=?",employee.getDepartment(), employeeId);
      }
      return getEmployee(employeeId);
   }

   public void deleteEmployee(int employeeId){
      db.update("delete from EMPLOYEELIST where employeeId=?",employeeId);
      throw new ResponseStatusException(HttpStatus.OK);
   }

    
}
