package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.Repo.UsersRepo;
import com.example.demo.entity.RegisterDto;
import com.example.demo.entity.Users;
import jakarta.validation.Valid;



@Controller
public class AccountController {
	
	@Autowired
	private UsersRepo repo;
	
	

	  @GetMapping("/register")
		public String registerUser(RegisterDto registerDto, Model model) {
	    	model.addAttribute(registerDto);
	    	model.addAttribute("success", false);
	    	
			return "register";	
		}
	

		@PostMapping("/register")
	    public String registerUser(
	    		Model model,
	    		@Valid @ModelAttribute RegisterDto registerDto,
	    		BindingResult result) {
	    	   
			if(!registerDto.getPassword().equals(registerDto.getCpassword())) {
				result.addError(
						new FieldError("registerDto", "cpassword",
								"password and Confirm Password don not Match"));
				
			}
			
			Users users = repo.findByUsername(registerDto.getUsername());
			if (users != null) {
				result.addError(
						new FieldError("registerDto", "username",
								"Username is already used"));
			}
			
			if(result.hasErrors()) {
				return "register";
			}
			try {
//				create new account 
				var bCryptEncoder = new BCryptPasswordEncoder();
				
				Users newUser = new Users();
				newUser.setName(registerDto.getName());
				newUser.setUsername(registerDto.getUsername());
				newUser.setEmail(registerDto.getEmail());
				newUser.setPhone(registerDto.getPhone());
				newUser.setPassword(registerDto.getPassword());
				newUser.setPassword (bCryptEncoder.encode (registerDto.getPassword()));
                 
				
				repo.save(newUser);
				
				model.addAttribute("registerDto", new RegisterDto());
				model.addAttribute("success", true);
				
			}
			catch(Exception ex) {
				result.addError(
						new FieldError("registerDto", "name", ex.getMessage())
						);
			}
			
	    		
				return "register";
	    }
		
		

}
