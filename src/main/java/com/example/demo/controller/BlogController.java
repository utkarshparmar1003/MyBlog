package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Admin;
import com.example.demo.model.Blog;
import com.example.demo.service.AdminService;
import com.example.demo.service.BlogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	
	/*@PostMapping("/login")
	public String login(@ModelAttribute User user, Model model) {
		User status= userService.loginUser(user.getUsername(), user.getPassword());
		if(status != null)
		{
			model.addAttribute("User registered Successfully !");
			return "homepage";
		}
		else
		{
			model.addAttribute("err","user not registered...!");
			return "auth";
		}
		 
	}
	
	@PostMapping("/registration")
	public String Registration(@ModelAttribute User user,Model model) {
		System.out.println(user);
		boolean status =userService.registerUser(user);
		if(status)
		{
			model.addAttribute("User registered Successfully !");
			return "redirect:/blog/index";
		}
		else
		{
			model.addAttribute("err","user not registered...!");
			
		}
		return"redirect:/blog/registrationpage";
		
		
		
	}
	*/
	@GetMapping("/index")
    public String loginPage() {
        return "auth";
    }	
	
	/*@GetMapping("/registrationpage")
	public String regPage(@ModelAttribute Admin admin)
	{
		return "registration";
	}
	*/
	@GetMapping("/home")
	public String listPosts(Model model) {
		model.addAttribute("posts", blogService.getAllPosts());
		return "homepage";
	}

	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("post", new Blog());
		return "create";
	}

	@PostMapping("/save")
	public String savePost(@ModelAttribute Blog post) {
		System.out.println(post);
		blogService.savePost(post);
		return "redirect:/blog/home";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable int id, Model model) {
		System.out.println(id+"Edit");
		model.addAttribute("post", blogService.getPostById(id));
		System.out.println(blogService.getPostById(id));
		return "edit1";
	}

	@PostMapping("/update/{id}")
	public String updatePost(@PathVariable int id, @ModelAttribute Blog post) {
		post.setId(id);
		System.out.println();
		blogService.savePost(post);
		return "redirect:/blog/home";
	}

	@GetMapping("/delete/{id}")
	public String deletePost(@PathVariable int id) {
		blogService.deletePost(id);
		return "redirect:/blog/home";
	}
	
	
	
	
	
	
	
	
	
	//login & register
		@Autowired
		private AdminService adminService;
		
		@GetMapping("/regPage")
		public String openRegPage(@ModelAttribute Admin admin,Model model)
		{
			model.addAttribute("admin", new Admin());
			return "registration";
		}
		
		@PostMapping("/regForm") 
		public String submitRegForm(@ModelAttribute Admin admin, Model model)
		{
			System.out.println(admin);
			boolean status = adminService.registerAdmin(admin);
			
			if(status)
			{
				model.addAttribute("successMsg", "Admin Registered Successfully!!");
				return "auth";
			}else
			{
				model.addAttribute("errorMsg", "Admin NOT Registered due to some error!!");
				return "registaration";
			}
			
			
		}
		
		@GetMapping("/")
		public String openLoginPage( Model model)		{
			model.addAttribute("admin", new Admin());
			return "auth";
		}
		
		@PostMapping("/loginForm")
		public String submitLoginForm(@ModelAttribute Admin admin, Model model)
		{
			Admin validAdmin = adminService.loginAdmin(admin.getEmail(), admin.getPassword());
					
			if(validAdmin != null)
			{
				model.addAttribute("myname",validAdmin.getName());
				System.out.println("login successful");
				return "redirect:/blog/home";
			}
			else
			{
				model.addAttribute("errorMsg", "Email id & password didn't match..!!");
				System.out.println("login unsuccessful");
				return "auth";
			}
		}
		
		@GetMapping("/Logout")
		public String Logout(HttpServletRequest request)
		{
			HttpSession session = request.getSession(false);
			
			if(session != null)
			{
				session.invalidate();
			}
			
			return "redirect:/blog/";
		}
}
