package com.example.formbindig_assignment.controller;

import com.example.formbindig_assignment.data.CustomerDao;
import com.example.formbindig_assignment.dto.CustomerFormDto;
import com.example.formbindig_assignment.entity.Customer;
import com.example.formbindig_assignment.entity.CustomerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

    private CustomerDao customerDao;

    @Autowired
    public CustomerController(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @GetMapping("customers/create")
    public ModelAndView getForm(){
        CustomerFormDto dto = new CustomerFormDto();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("form",dto);
        modelAndView.setViewName("create-customer");
        return modelAndView;
    }

    @PostMapping("customers/process")
    public String processForm(@Valid @ModelAttribute("form") CustomerFormDto dto, BindingResult bindingResult){
        if (customerDao.findByEmail(dto.getEmail()).isPresent()){
            FieldError emailError = new FieldError("form","email","Email "+dto.getEmail()+" already exists");
            bindingResult.addError(emailError);
        }
        if(bindingResult.hasErrors()){
            return "create-customer";
        }
        if (dto.getCellPhone() == null) dto.setCellPhone("");
        if (dto.getCity() == null) dto.setCity("");
        if (dto.getHomePhone() == null) dto.setHomePhone("");
        if (dto.getZipCode() == null) dto.setZipCode("");
        Customer customer = new Customer(dto.getEmail(),
                new CustomerDetails(dto.getStreet(),dto.getZipCode(),dto.getCity(),dto.getHomePhone(),dto.getCellPhone()));
        customer = customerDao.save(customer);
        return "redirect:/customers?type=id&value="+customer.getCustomerId();
    }

    @GetMapping("/search")
    public String goToSearch(RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("type","email");
        redirectAttributes.addAttribute("value","");
        return "redirect:/customers";
    }

    @GetMapping("customers")
    public String find(@RequestParam("type") String type, @RequestParam("value") String value, Model model){
        Optional<Customer> customer = Optional.empty();
        List<Customer> customerList = new ArrayList<>();
        switch (type){
            case "id": customer = customerDao.findById(value);
                break;
            case "email": customer = customerDao.findByEmail(value);
                break;
            case "all": customerList = customerDao.findAll();
                break;
            default: throw new IllegalArgumentException("Type "+type+" is invalid");
        }
        if (customer.isPresent()) model.addAttribute("customer",customer.get());

        else if(customerList.size() > 0) model.addAttribute("customerList",customerList);

        else if(value.equalsIgnoreCase("")) model.addAttribute("message","Search...");

        else model.addAttribute("message","Customer could not be found");

        return "customer-view";
    }

    @GetMapping("customers/find")
    public String findDetailsById(@RequestParam(name = "id") String id,Model model){
        Customer customer = customerDao.findById(id).get();
        model.addAttribute("customer",customer);
        return "customer-details";
    }

    @PostMapping("delete")
    public String delete(@RequestParam("id") String id){
        customerDao.delete(id);
        return "redirect:/search";
    }
}
