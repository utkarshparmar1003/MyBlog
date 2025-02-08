package com.example.demo.service;

import com.example.demo.model.Admin;

public interface AdminService {

public boolean registerAdmin(Admin admin);
	
	public Admin loginAdmin(String email, String password);
}
