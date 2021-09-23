package ca.sheridancollege.mislim.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dog { 
	
	private int entryNum;
	private String name;
	private String ownerName;
	private int breedId;
	private String gender;
	private String classSpecialty;

}
