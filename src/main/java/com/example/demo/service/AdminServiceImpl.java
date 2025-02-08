package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public boolean registerAdmin(Admin admin) {
		try
		{
			adminRepository.save(admin);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Admin loginAdmin(String email, String password) {
		Admin validAdmin = adminRepository.findByEmail(email);
		if(validAdmin != null && validAdmin.getPassword().equals(password))
		{
			return validAdmin;
		}
		return null;
	}
}
