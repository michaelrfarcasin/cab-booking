package auth.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity(name = "user_details")
public class User {
	@Id
	private int id;
	
	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

	@NotEmpty
	private String roles;

	@NotNull
	private boolean enabled;
	
	public User() {
		this(0, "", "", "", false);
	}
	
	public User(int id, String username, String password, String roles, boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.enabled = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + username + ", password=" + password + ", enabled=" + enabled + "]";
	}
}
