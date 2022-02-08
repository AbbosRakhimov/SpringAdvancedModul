package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.pdp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	boolean existsByPhonNumberAndIdNot(String phonNumber, Integer id);
	
	boolean existsByPhonNumberOrFullName(String phonNumber, String name);
}
