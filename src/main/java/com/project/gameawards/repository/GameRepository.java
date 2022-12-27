package com.project.gameawards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.gameawards.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {

}
