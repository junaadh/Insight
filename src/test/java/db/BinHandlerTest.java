package db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertTrue;

import forms.User;
import helper.Misc.prefix;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BinHandlerTest {

	User userr = new User("A999999", "ahmed000", "ahmed ali", "123456", 21, "m", "mail@mail.com", 7770000, "jun777");
	User usz = new User("A000000", "ali000", "ali ahmed", "334445", 22, "m", "mail@mail.com", 7009000, "ux99088");
	BinHandler handler = new BinHandler();

	@Test
	public void TestAwriteUserData() throws Exception {
		String test = handler.addUser(userr);
		assertTrue(test != null);
	}

	@Test
	public void TestBwrite2UserData() {
		String test = handler.addUser(usz);
		System.out.println(test);
		assertTrue(test != null);
	}

	@Test
	public void TestCreadUserDataNid() {
		User uszA = handler.searchUsers(prefix.NID, "A000000");
		assertEquals(uszA.buildInfo(), usz.buildInfo());
	}

	@Test
	public void TestDreadUserDataUsername() {
		User userrA = handler.searchUsers(prefix.USERNAME, "ahmed000");
		assertEquals(userrA.buildInfo(), userr.buildInfo());
	}

	@Test
	public void TestEupdateUserData() throws IOException {
		User uszB = handler.updateUser(usz, "mail@mail.com", "mail@gmail.com", prefix.EMAIL);

		// assertTrue(bool);
		assertEquals(uszB.getEmail(), "mail@gmail.com");
	}

	@Test
	public void TestFdeleteUserData() {
		boolean test = handler.deleteUser(usz);
		assertTrue(test);
	}

	@Test
	public void TestGdelete2UserData() {
		boolean test = handler.deleteUser(userr);
		assertTrue(test);
	}

}
