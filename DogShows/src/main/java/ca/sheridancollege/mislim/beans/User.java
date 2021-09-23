package ca.sheridancollege.mislim.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	private Long userId;
	private String userName;
	private String encryptedPassword;

}
