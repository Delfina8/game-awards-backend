package com.project.gameawards.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.project.gameawards.model.Game;
import com.project.gameawards.repository.GameRepository;
import com.project.gameawards.service.GameService;
import com.project.gameawards.service.exception.BusinessException;
import com.project.gameawards.service.exception.NoContentException;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Override
	public List<Game> findAll() {
		return gameRepository.findAll(Sort.by(Direction.DESC, "votes"));
	}

	@Override
	public Game findById(Long id) {
		Optional<Game> game = gameRepository.findById(id);
		return game.orElseThrow(() -> new NoContentException());
	}

	@Override
	public void insert(Game game) {
		if(game.getId() != null) {
			throw new BusinessException("O jogo já existe!");
		}
		gameRepository.save(game);
		
	}

	@Override
	public void update(Long id, Game game) {
		Game gameDb = findById(id); //gameDB = game database
		if(gameDb.getId().equals(game.getId())) {
			gameRepository.save(game);
		} else {
			throw new BusinessException("Os ID´s são divergentes!");
		}
		
	}

	@Override
	public void delete(Long id) {
		Game gameDB = findById(id);
		gameRepository.delete(gameDB);
		
	}

	@Override
	public void vote(Long id) {
		Game gameDB = findById(id);
		gameDB.setVotes(gameDB.getVotes()+1);
		
		update(id, gameDB);
	}

}

