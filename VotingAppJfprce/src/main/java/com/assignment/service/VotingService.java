package com.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.model.LoggedUser;
import com.assignment.model.User;
import com.assignment.model.VoteCount;
import com.assignment.model.Voting;
import com.assignment.repository.UserRepository;
import com.assignment.repository.VotingRepository;

@Service
public class VotingService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VotingRepository votingRepository;
	
	public boolean addUser(User user) {
		User foundUser = this.userRepository
				             .findByUsername(user.getUsername()).orElse(null);
		if(foundUser == null) {
			this.userRepository.save(user);
			return true;
		}
		else
			return false;
	}

	public User login(LoggedUser loggedUser) {
		User foundUser = this.userRepository
				             .findByUsernameAndPassword(loggedUser.getUsername(), loggedUser.getPassword()).orElse(null);
		
		return foundUser;
	}
	
	public User logout(LoggedUser loggedUser) {
		User foundUser = this.userRepository
				             .findByUsernameAndPassword(loggedUser.getUsername(), loggedUser.getPassword()).orElse(null);
		
		return foundUser;
	}
	
	

	public Voting findByUserId(Integer id) {
		Voting findVote = this.votingRepository.findByUserId(id).orElse(null);		
		return findVote;
	}

	public void addVote(Voting voting) {
		this.votingRepository.save(voting);		
	}

	public List<VoteCount> findAllVote() {
		return this.votingRepository.countVoteByCandidateName();
	}

}
