package uz.pdp.payload;

import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto {

	@NotNull(message = "Name can not be empty")
	private String fullName;
	
	@NotNull(message = "PhonNumber can not be empty")
	private String phonNumber;
	
	@NotNull(message = "Adresse can not be empty")
	private String adresse;
}
