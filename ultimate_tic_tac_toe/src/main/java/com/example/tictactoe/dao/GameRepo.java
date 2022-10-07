package com.example.tictactoe.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.tictactoe.models.Game;

@Repository
public interface GameRepo extends CrudRepository<Game, Long> {
}
