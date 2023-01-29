package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.assignment.model.LoggedUser;
import com.assignment.model.User;
import com.assignment.model.VoteCount;
import com.assignment.model.Voting;
import com.assignment.service.VotingService;



@Controller
public class VotingController {
	@Autowired
	private VotingService votingService;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("loggeduser",new LoggedUser());
		return "login";	
	}
	
	
	@GetMapping("/logout")
	public String logout(@ModelAttribute LoggedUser loggedUser, Model model) {
		this.votingService.logout(loggedUser);
		return "redirect:/";
	}
	
	

	@PostMapping("/login")
	public String login(@ModelAttribute LoggedUser loggedUser, Model model) {
		if(loggedUser.getUsername().equals("admin") 
				&& loggedUser.getPassword().equals("admin"))
		{
			List<VoteCount> votes = this.votingService.findAllVote();
			model.addAttribute("votes",votes);
			return "adminpage";
		}
		else {
			User foundUser = this.votingService.login(loggedUser);		
			if(foundUser == null) {
				model.addAttribute("error","Username or Password is invalid");
				model.addAttribute("loggeduser",new LoggedUser());
				return "login";
			}
			else {
				Voting voter = new Voting();
		     	voter.setUserId(foundUser.getId()); 
				model.addAttribute("voter",voter);
				return "votingpage";
			}
		}
	}
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("user",new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute User user, Model model) {
		boolean result = this.votingService.addUser(user);
		if(result) 
			return "redirect:";
		else {
			model.addAttribute("error","User Already Exists");
			model.addAttribute("user",new User());
			return "register";
		}
	}
	
	@PostMapping("/voted/{userId}")
	public String voted(@ModelAttribute Voting voting, Model model, @PathVariable Integer userId) {
		Voting findVote = this.votingService.findByUserId(userId);
		
		if(findVote != null) {	
			model.addAttribute("loggeduser",new LoggedUser());
			model.addAttribute("error","You have alreday voted once to " + findVote.getCandidateName());
			Voting voter = new Voting();
	     	voter.setUserId(userId); 
			model.addAttribute("voter",voter);
			return "votingpage";

			
		}
		else {
			this.votingService.addVote(voting);
			model.addAttribute("error","You have voted Successfully");
			Voting voter = new Voting();
	     	voter.setUserId(userId); 
			model.addAttribute("voter",voter);

			return "votingpage";
		}
	}

}
