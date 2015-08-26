package com.company.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
	
	@Entity
	@Table(name="users")
	public class User implements Serializable{	
	
		private static final long serialVersionUID = 1987713877489520615L;

	@NotBlank(groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Size(min=5, max=45, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Pattern(regexp="^\\w{5,}", groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Id
	@Column(name="username")
	private String username;
	
	@NotBlank(groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Pattern(regexp="^\\S+$", groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Size(min=5, max= 45, groups={FormValidationGroup.class})
	private String password;
	
	@Pattern(regexp ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$", groups={FormValidationGroup.class}) // for some reason junit do not accept
	@Size(min=5, max= 50, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String email;
	
	@NotBlank(groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Pattern(regexp="^\\S+$", groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Size(min=2, max= 45, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String name;
	
	private boolean enabled = false;
	
	@NotNull(groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String authority;
	
	@NotBlank(groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Size(min=2, max= 50, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String city;
	
	public User(){}
	
	public User(String username, String password, String email, String name,
			boolean enabled, String authority, String city) {		
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.enabled = enabled;
		this.authority = authority;
		this.city = city;	
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", email=" + email + ", name=" + name + ", enabled="
				+ enabled + ", authority=" + authority + ", city=" + city + "]";
	}
	
}
