package com.ashokit.service;

import java.util.List;

import com.ashokit.dao.ContactDao;
import com.ashokit.exception.NoDataFoundException;
import com.ashokit.model.Contact;

public class ContactServiceImpl implements ContactService {

	private ContactDao contactDao;

	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	public String getNameById(Integer id) {
		String name = contactDao.findNameById(id);
		String formattedName = name.toUpperCase();
		return formattedName;
	}

	public List<String> getAllContactNames() {
		List<String> names = contactDao.findNames();
		if (!names.isEmpty()) {
			return names;
		}
		return null;
	}

	public Contact getContactById(Integer id) {
		Contact contact = contactDao.findById(id);
		if (contact == null) {
			throw new NoDataFoundException();
		}
		return contact;
	}

}
