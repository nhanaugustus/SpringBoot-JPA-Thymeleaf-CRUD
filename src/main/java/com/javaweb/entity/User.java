package com.javaweb.entity;


import java.util.HashSet;

import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 128, nullable = false, unique = true)
	private String email;
	
	@Column(name = "first_name",length = 45, nullable = false)
	private String firstname;
	
	@Column(name = "last_name",length = 45, nullable = false)
	private String lastname;
	
	@Column(length = 64, nullable = false)
	private String password;
	
	private boolean enabled;
	
	@Column(length = 64)
	private String photos;
	
	
	@ManyToMany()
	@JoinTable(
			name="users_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private  Set<Role> roles = new HashSet<>();


	public User(Integer id, String email, String firstname, String lastname, String password, boolean enabled,
			String photos, Set<Role> roles) {
		super();
		this.id = id;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.enabled = enabled;
		this.photos = photos;
		this.roles = roles;
	}


	public User() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getPhotos() {
		return photos;
	}


	public void setPhotos(String photos) {
		this.photos = photos;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public User(String email, String firstname, String lastname, String password) {
		super();
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}
	
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
		
	
	@Override
	public String toString() {
		return ""+this.id+":"+this.firstname+":"+this.lastname+":"+this.password+":"+this.photos+":"+this.roles;
	}
	
	
	public String getPhotosImagePath() {
		return "/user-photos/"+this.id+"/"+this.getPhotos();
	}
	
	

}
