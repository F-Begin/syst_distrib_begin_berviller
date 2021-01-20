package begber.web.servlet.users;

public class User {
	private int id;
	private String username;
	private String password;
	private int permission;
	
	public User(int id, String username, String password, int perm) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.permission = perm;
	}
	
	public boolean checkPass(String pass) {
		if(this.getPassword().equals(pass))
			return true;
		return false;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}
	
	
}
