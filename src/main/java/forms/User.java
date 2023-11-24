package forms;

import helper.Misc.prefix;

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
			String nationality,
			String userId) {
		super(nid, username, fullname, password, age, gender, email, phoneNo, nationality, false, false);
		this.userId = userId;
	}

	public User(String[] args) {
		super(args);
		this.userId = args[12];
	}

	public User() {
		// dummy
		super();
	}

	public String getUserId() {
		return this.userId;
	}

	public String buildInfo() {
		StringBuilder fd = new StringBuilder(super.buildInfo());
		fd.append(prefix.USERID.getPrefix() + this.userId);
		return fd.toString();
	}
}
