package forms;

public class User extends Person {

	private String userId;

	public User(
			String nid,
			String username,
			String fullname,
			String password,
			int age,
			String gender,
			String email,
			int phoneNo,
			String userId) {
		super(nid, username, fullname, password, age, gender, email, phoneNo, false, false);
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}
}
