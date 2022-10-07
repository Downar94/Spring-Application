package com.example.tictactoe.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.tictactoe.models.Player;
@Repository
public interface PlayerRepo extends CrudRepository<Player, Long>{

}
