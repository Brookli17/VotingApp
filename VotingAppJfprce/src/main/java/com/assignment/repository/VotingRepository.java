package com.assignment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.model.VoteCount;
import com.assignment.model.Voting;

@Repository
public interface VotingRepository extends CrudRepository<Voting, Integer> {
	Optional<Voting> findByUserId(Integer userId);
	
	//@Query("select v.candidateName, count(v.candidateName) from Voting as v group by v.candidateName")
	@Query("SELECT new com.assignment.model.VoteCount(v.candidateName, COUNT(v.candidateName)) "
			  + "FROM Voting AS v GROUP BY v.candidateName")
	List<VoteCount> countVoteByCandidateName();
}
