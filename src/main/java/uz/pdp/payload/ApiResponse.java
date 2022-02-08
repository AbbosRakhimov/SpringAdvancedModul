package uz.pdp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

	private String message;
	
	private boolean success;
	
	Object object;

	public ApiResponse(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	
}
