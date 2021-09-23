package ca.sheridancollege.mislim.repositories;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;


import ca.sheridancollege.mislim.beans.User;
@Repository
public class SecurityRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	public User findUserAccount(String userName) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM sec_user where userName=:userName";
		parameters.addValue("userName", userName);
		ArrayList<User> users = (ArrayList<User>)jdbc.query(query, parameters,new BeanPropertyRowMapper<User>(User.class));
		if (!users.isEmpty())
			return users.get(0);
		else
			return null;
		
			}
	
	public List<String> getRolesById(long userId) {
		
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "select user_role.userId, sec_role.roleName " 
		+ "FROM user_role, sec_role "+ "WHERE user_role.roleId=sec_role.roleId " 
				+ "and userId=:userId";
		parameters.addValue("userId", userId);
		
		ArrayList<String> roles = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			String role = (String)row.get("roleName");
			roles.add(role);
			}
		return roles;
		}
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void addUser(String userName, String password) {
		MapSqlParameterSource parameters =new MapSqlParameterSource();
		String query = "insert into SEC_User "+
		"(userName, encryptedPassword, ENABLED)"+ 
				" values (:userName, :pass, 1)";
		parameters.addValue("userName", userName);
		parameters.addValue("pass", encoder.encode(password));
		jdbc.update(query, parameters);
	}
	
	public void addRole(long userId, long roleId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "insert into user_role (userId, roleId)"+ 
				"values (:userId, :roleId);";
		parameters.addValue("userId", userId);
		parameters.addValue("roleId", roleId);
		jdbc.update(query, parameters);
	}
	

}
