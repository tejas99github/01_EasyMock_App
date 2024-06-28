package com.ashokit.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ashokit.dao.ContactDao;
import com.ashokit.exception.NoDataFoundException;
import com.ashokit.model.Contact;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class ContactServiceTest {

	public static ContactServiceImpl service = null;

	@BeforeClass
	public static void init() {

		// creating proxy
		ContactDao daoProxy = EasyMock.createMock(ContactDao.class);

		// setting behaviour for dao method 01
		EasyMock.expect(daoProxy.findNameById(101)).andReturn("Ashok");
		EasyMock.expect(daoProxy.findNameById(102)).andReturn("AshokIT");

		// set behaviour for dao method 02
		List<String> names = new ArrayList();
		names.add("tejas");
		names.add("Akshay");
		names.add("Sharad");
		EasyMock.expect(daoProxy.findNames()).andReturn(names);

		// set behaviour for dao method 03
		Contact c = new Contact();
		c.setContactId(101);
		c.setContactName("Virat");
		c.setContactNo(53532);

		EasyMock.expect(daoProxy.findById(101)).andReturn(c);
		EasyMock.expect(daoProxy.findById(201)).andReturn(null);

		// saving proxy behaviour
		EasyMock.replay(daoProxy);

		// injecting proxy object into target object
		service = new ContactServiceImpl();
		service.setContactDao(daoProxy);

	}

	@Test
	@Ignore // use to ignore the test case while testing
	public void testGetNameById_01() {
		String name = service.getNameById(101);
		assertNotNull(name);
	}

	@Test
	@Ignore
	public void testGetNames_02() {
		List<String> allContactNames = service.getAllContactNames();
		assertNotNull(allContactNames);
	}

	@Test
	@Ignore
	public void testGetContactById_03() {
		Contact contact = service.getContactById(101);
		assertNotNull(contact);
	}

	@Test(expected = NoDataFoundException.class)
	public void testGetContactById_04() {
		service.getContactById(201);
	}

}
