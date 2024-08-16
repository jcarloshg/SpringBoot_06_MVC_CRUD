package com.luv2code.springboot.thymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleaf.entity.Employee;
import com.luv2code.springboot.thymeleaf.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("list")
    public String listEmployees(Model model) {
        List<Employee> employees = this.employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employees/listEmployees";
    }

    // ============================================================
    // >>> Add employee
    // ============================================================

    @GetMapping("/formAdd")
    public String getFormAdd(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employees/formAdd";
    }

    @PostMapping("save")
    public String save(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

    // ============================================================
    // >>> Update
    // ============================================================

    @GetMapping("/update")
    public String getFormUpdate(@RequestParam("employeeID") int id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "employees/formAdd";
    }

    // ============================================================
    // >>> Delete
    // ============================================================

    @GetMapping("/delete")
    public String deleteByID(@RequestParam("employeeID") int id) {
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }

}
