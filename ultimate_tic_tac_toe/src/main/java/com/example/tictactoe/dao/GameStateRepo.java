package com.example.tictactoe.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.tictactoe.models.GameState;

@Repository
public interface GameStateRepo extends CrudRepository<GameState, Long>{

}
