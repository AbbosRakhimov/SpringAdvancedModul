package uz.pdp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String fullName;
	
	@Column(nullable = false, unique = true)
	private String phonNumber;
	
	@Column(nullable = false)
	private String adresse;

	public Customer(String fullName, String phonNumber, String adresse) {
		super();
		this.fullName = fullName;
		this.phonNumber = phonNumber;
		this.adresse = adresse;
	}
	
}
