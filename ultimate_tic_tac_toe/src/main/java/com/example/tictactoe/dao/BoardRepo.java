package com.example.tictactoe.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.tictactoe.models.Board;

@Repository
public interface BoardRepo extends CrudRepository<Board, Long>{

}
