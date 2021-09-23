package ca.sheridancollege.mislim.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.mislim.beans.Breed;
import ca.sheridancollege.mislim.beans.Dog;

@Repository
public class DogShowRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	public void addDog(Dog dog) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO dogs (name, ownerName, breedId, gender, classSpecialty) "
				+ "VALUES (:name, :ownerName,:breedId, :gender, :classSpecialty)";

		parameters.addValue("name", dog.getName());
		parameters.addValue("ownerName", dog.getOwnerName());
		parameters.addValue("breedId", dog.getBreedId());
		parameters.addValue("gender", dog.getGender());
		parameters.addValue("classSpecialty", dog.getClassSpecialty());

		jdbc.update(query, parameters);
	}

	public void addBreed(Breed breed) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO breed (breedName) VALUES (:breedName)";

		parameters.addValue("breedName", breed.getBreedName());
		jdbc.update(query, parameters);
	}

	public ArrayList<Breed> getBreeds() {

		String query = "Select * from breed order by breedId ";
		ArrayList<Breed> breeds = new ArrayList<Breed>();
		List<Map<String, Object>> rows = jdbc.queryForList(query, new HashMap<String, Object>());

		for (Map<String, Object> row : rows) {
			Breed breed = new Breed();

			breed.setBreedId((int) row.get("breedId"));
			breed.setBreedName((String) row.get("breedName"));
			breeds.add(breed);
		}
		return breeds;
	}

	public ArrayList<String> getDogBreedName() {
		String query = "Select a.breedName from breed  a inner join "+
	             " dogs b on a.breedId = b.breedId order by b.breedId;";
		ArrayList<String> breeds = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbc.queryForList(query,new HashMap<String, Object>());
		for (Map<String, Object> row : rows) {
			String breedName = (String)row.get("breedName");
			breeds.add(breedName);
		}
		return breeds;
	}
	
	public ArrayList<String> getBreedByOwner(String ownerName) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "Select a.breedName from breed  a inner join "+
	             " dogs b on a.breedId = b.breedId where ownerName =:owner  order by b.breedId;";
		parameters.addValue("owner", ownerName);
		ArrayList<String> breeds = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbc.queryForList(query,parameters);
		for (Map<String, Object> row : rows) {
			String breedName = (String)row.get("breedName");
			breeds.add(breedName);
		}
		return breeds;
	}
	public ArrayList<String> getShowList() {

		String query1 = "Select  count(entryNum) as num, a.breedName,a.breedId as id from breed a inner "
				+ " join  dogs b on a.breedId = b.breedId group by breedName,a.breedId ORDER by a.breedId ;";

		String query2 = "Select  count(entryNum) as mc,breedId id  from  dogs "
				+ "WHERE (gender = 'Male' and classspecialty = 'Class') GROUP by breedId   ORDER by breedId;";

		String query3 = "Select  count(entryNum) as fc,breedId id  from  dogs "
				+ "WHERE (gender = 'Female' and classspecialty = 'Class') GROUP by breedId   ORDER by breedId;";

		String query4 = "Select  count(entryNum) as ms,breedId id  from  dogs "
				+ "WHERE (gender = 'Male' and classspecialty = 'Specialty') GROUP by breedId   ORDER by breedId;";

		String query5 = "Select  count(entryNum) as fs,breedId id  from  dogs "
				+ "WHERE (gender = 'Female' and classspecialty = 'Specialty') GROUP by breedId   ORDER by breedId;";

		List<Map<String, Object>> rows1 = jdbc.queryForList(query1, new HashMap<String, Object>());
		List<Map<String, Object>> rows2 = jdbc.queryForList(query2, new HashMap<String, Object>());
		List<Map<String, Object>> rows3 = jdbc.queryForList(query3, new HashMap<String, Object>());
		List<Map<String, Object>> rows4 = jdbc.queryForList(query4, new HashMap<String, Object>());
		List<Map<String, Object>> rows5 = jdbc.queryForList(query5, new HashMap<String, Object>());

		ArrayList<String> dogShow = new ArrayList<String>();

		for (Map<String, Object> row1 : rows1) {

			String show = row1.get("num") + " " + row1.get("breedName") + "  <|==========|>  ";
			if (!rows2.isEmpty()) {
				String data = "0";
				for (Map<String, Object> row2 : rows2) {

					if ((int) row1.get("id") == (int) row2.get("id")) {
						data = "" + (long) row2.get("mc");
					}
				}
				show = show + data;
			} else {
				show = show + 0;
			}

			if (!rows3.isEmpty()) {
				String data = "0";
				for (Map<String, Object> row3 : rows3) {

					if ((int) row1.get("id") == (int) row3.get("id")) {
						data = "" + (long) row3.get("fc");
					}
				}
				show = show + "-" + data;
			} else {
				show = show + "-" + 0;
			}

			if (!rows4.isEmpty()) {
				String data = "0";
				for (Map<String, Object> row4 : rows4) {

					if ((int) row1.get("id") == (int) row4.get("id")) {
						data = "" + (long) row4.get("ms");
					}
				}
				show = show + "-" + data;
			} else {
				show = show + "-" + 0;
			}

			if (!rows5.isEmpty()) {
				String data = "0";
				for (Map<String, Object> row5 : rows5) {

					if ((int) row1.get("id") == (int) row5.get("id")) {
						data = "" + (long) row5.get("fs");
					}
				}
				show = show + "-" + data;
			} else {
				show = show + "-" + 0;
			}

			dogShow.add(show);
		}
		return dogShow;
	}

	public ArrayList<Dog> getDogs() {

		String query = "Select * from dogs order by breedId";
		ArrayList<Dog> dogs = new ArrayList<Dog>();
		List<Map<String, Object>> rows = jdbc.queryForList(query, new HashMap<String, Object>());

		for (Map<String, Object> row : rows) {
			Dog dog = new Dog();
			dog.setEntryNum((int) row.get("entryNum"));
			dog.setName((String) row.get("name"));
			dog.setOwnerName((String) row.get("ownerName"));
			dog.setBreedId((int) row.get("breedId"));
			dog.setGender((String) row.get("gender"));
			dog.setClassSpecialty((String) row.get("classSpecialty"));

			dogs.add(dog);
		}
		return dogs;
	}

	public Dog getDogById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "Select * from dogs where entryNum=:id";
		parameters.addValue("id", id);
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		ArrayList<Dog> dogs = new ArrayList<Dog>();

		for (Map<String, Object> row : rows) {
			Dog dog = new Dog();
			dog.setEntryNum((int) row.get("entryNum"));
			dog.setName((String) row.get("name"));
			dog.setOwnerName((String) row.get("ownerName"));
			dog.setBreedId((int) row.get("breedId"));
			dog.setGender((String) row.get("gender"));
			dog.setClassSpecialty((String) row.get("classSpecialty"));

			dogs.add(dog);
		}
//		if (dogs.isEmpty())
//			return null;
		return dogs.get(0);
	}

	public void editDog(Dog dog) {
		String query = "UPDATE dogs SET name=:name, ownerName=:ownerName,"
				+ "breedId=:breedId, gender=:gender, classSpecialty=:classSpecialty WHERE entryNum=:id";

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", dog.getEntryNum());
		parameters.addValue("name", dog.getName());
		parameters.addValue("ownerName", dog.getOwnerName());
		parameters.addValue("breedId", dog.getBreedId());
		parameters.addValue("gender", dog.getGender());
		parameters.addValue("classSpecialty", dog.getClassSpecialty());

		jdbc.update(query, parameters);
	}

	public void deleteById(int id) {
		String query = "delete from dogs where entryNum=:id";

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		jdbc.update(query, parameters);

	}
	
	public ArrayList<Dog> getDogByOwner(String ownerName) {//owner name is unique(user)
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "Select * from dogs where ownerName=:name order by breedId";
		parameters.addValue("name", ownerName);
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		ArrayList<Dog> dogs = new ArrayList<Dog>();

		for (Map<String, Object> row : rows) {
			Dog dog = new Dog();
			dog.setEntryNum((int) row.get("entryNum"));
			dog.setName((String) row.get("name"));
			dog.setOwnerName((String) row.get("ownerName"));
			dog.setBreedId((int) row.get("breedId"));
			dog.setGender((String) row.get("gender"));
			dog.setClassSpecialty((String) row.get("classSpecialty"));

			dogs.add(dog);
		}
//		if (dogs.isEmpty())
//			return null;
		return dogs;
	}

}
