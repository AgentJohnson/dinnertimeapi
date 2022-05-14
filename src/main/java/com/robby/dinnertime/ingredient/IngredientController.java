package com.robby.dinnertime.ingredient;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EmployeeController {

  private final IngredientRepository repository;

  EmployeeController(IngredientRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/ingredients")
  List<Ingredient> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/ingredients")
  Ingredient newEmployee(@RequestBody Ingredient newEmployee) {
    return repository.save(newEmployee);
  }

  // Single item
  
  @GetMapping("/ingredients/{id}")
  Ingredient one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new IngredientNotFoundException(id));
  }

  @PutMapping("/ingredients/{id}")
  Ingredient replaceEmployee(@RequestBody Ingredient newEmployee, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(employee -> {
        employee.setName(newEmployee.getName());
        employee.setRole(newEmployee.getRole());
        return repository.save(employee);
      })
      .orElseGet(() -> {
        newEmployee.setId(id);
        return repository.save(newEmployee);
      });
  }

  @DeleteMapping("/ingredients/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
