package com.project.gameawards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.gameawards.model.Game;
import com.project.gameawards.service.GameService;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GameController extends BaseRestController {
	
	@Autowired
	private GameService businessLayer; //Vai ter acesso apenas as assinaturas do método e não as implementações
	/*
	 * Enpoint que lista todos os games. Consumido por um App React Native
	 * retorna a Lista de games ordenada pela quantidade de votos.
	 */
	@GetMapping
	public ResponseEntity<List<Game>> findAll(){
		List<Game> games = businessLayer.findAll();
		return ResponseEntity.ok(games);
	}
	
	//Endpoint que vota em um jogo/game específico
	@PatchMapping("/{id}/vote")
	public ResponseEntity<Void> vote(@PathVariable Long id){
		this.businessLayer.vote(id);
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Game> findById(@PathVariable Long id){
		Game game = businessLayer.findById(id);
		return ResponseEntity.ok(game);
	}
	
	@PostMapping
	public ResponseEntity<Game> insert (@RequestBody Game game) {
		businessLayer.insert(game);
		return ResponseEntity.status(HttpStatus.CREATED).body(game);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Game> update(@PathVariable Long id, @RequestBody Game game){
		businessLayer.update(id, game);
		return ResponseEntity.ok(game);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Game> delete (@PathVariable Long id) {
		businessLayer.delete(id);
		return ResponseEntity.ok().build();
	}
	
}

