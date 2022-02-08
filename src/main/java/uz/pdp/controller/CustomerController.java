package uz.pdp.controller;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.experimental.PackagePrivate;
import uz.pdp.entity.Customer;
import uz.pdp.payload.ApiResponse;
import uz.pdp.payload.CustomerDto;
import uz.pdp.service.CustomerService;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

	@Autowired 
	CustomerService customerService;
	
	/**
	 * this method returns customer list as a page
	 * @param page 
	 * @return Customer
	 */
	@GetMapping
	public HttpEntity<?> getCutomer(@RequestParam int page){
		Page<Customer> cutoPage = customerService.getCustomers(page);
		return ResponseEntity.status(HttpStatus.OK).body(cutoPage);
	}
	/**
	 * 
	 * @return Customer list
	 */
	@GetMapping(value = "/list")
	public HttpEntity<?> getCustomerList(){
		List<Customer> customers = customerService.getCutomerLIst();
		return ResponseEntity.status(HttpStatus.OK).body(customers);
	}
	/**
	 * 
	 * @param id
	 * @return One Customer Object
	 */
	@GetMapping(value = "/{id}")
	public HttpEntity<?> getCutomer(@PathVariable Integer id){
		ApiResponse apiResponse = customerService.getCustomer(id);
		return ResponseEntity.status(customerService.getCustomer(id).isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
	}
	/**
	 * this method validates that client does not send empty fields and stores it in the database
	 * @param customerDto
	 * @return ApiResponse
	 */
	@PostMapping
	public HttpEntity<?> addCustomer( @Valid @RequestBody CustomerDto customerDto){
		ApiResponse apiResponse = customerService.addCustomer(customerDto);
		return ResponseEntity.status(customerService.addCustomer(customerDto).isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
	}
	@PutMapping(value = "/{id}")
	public HttpEntity<?> editCutomer(@PathVariable Integer id, @Valid @RequestBody CustomerDto customerDto){
		ApiResponse apiResponse = customerService.editCutomer(id, customerDto);
		return ResponseEntity.status(customerService.editCutomer(id, customerDto).isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
		
	}
	@DeleteMapping(value = "/{id}")
	public HttpEntity<?> deleteCustomer(@PathVariable Integer id) {
		ApiResponse apiResponse = customerService.deleteCustomer(id);
		return ResponseEntity.status(customerService.deleteCustomer(id).isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
	}
	
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
