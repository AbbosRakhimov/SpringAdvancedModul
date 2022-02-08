package uz.pdp.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import uz.pdp.entity.Customer;
import uz.pdp.payload.ApiResponse;
import uz.pdp.payload.CustomerDto;
import uz.pdp.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	public Page<Customer> getCustomers(int page){
		Pageable pageable = PageRequest.of(page, 5);
		
		Page<Customer> page2 = customerRepository.findAll(pageable);
		return page2;
	}
	public List<Customer> getCutomerLIst(){
		return customerRepository.findAll();
	}
	public ApiResponse getCustomer(Integer id) {
		Optional<Customer> cOptional = customerRepository.findById(id);
		if(cOptional.isPresent()) {
			return new ApiResponse("Curomer exist", true, cOptional.get());
		}
		return new ApiResponse("Cutomer not found", false);
	}
	public ApiResponse addCustomer(CustomerDto customerDto) {
		if(customerRepository.existsByPhonNumberOrFullName(customerDto.getPhonNumber(), customerDto.getFullName()))
			return new ApiResponse("Customer already exist", false);
		Customer customer = new Customer(customerDto.getFullName(), customerDto.getPhonNumber(),customerDto.getAdresse());
		customerRepository.save(customer);
		return new ApiResponse("Customer successfuly added", true);
	}
	public ApiResponse editCutomer(Integer id, CustomerDto customerDto) {
		if(customerRepository.existsByPhonNumberAndIdNot(customerDto.getPhonNumber(), id))
			return new ApiResponse("Cutomer already exist", false);
		Optional<Customer> cOptional = customerRepository.findById(id);
		if(!cOptional.isPresent())
			return new ApiResponse("Cutomer not found", false);
		Customer customer = cOptional.get();
		customer.setFullName(customerDto.getFullName());
		customer.setPhonNumber(customer.getPhonNumber());
		customer.setAdresse(customerDto.getAdresse());
		customerRepository.save(customer);
		return new ApiResponse("Customer successfully edited", true);
	}
	public ApiResponse deleteCustomer(Integer id) {
		Optional<Customer> cOptional = customerRepository.findById(id);
		if(cOptional.isPresent()) {
			customerRepository.deleteById(id);
			return new ApiResponse("Customer successfuly deleted", true);
		}
		return new ApiResponse("Customer not found", false);
	}
}
