package com.ashokit.service;

import java.util.List;

import com.ashokit.model.Contact;

public interface ContactService {

	public String getNameById(Integer id);

	public List<String> getAllContactNames();

	public Contact getContactById(Integer id);

}
