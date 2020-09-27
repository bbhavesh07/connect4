package com.pratilipi.connect4.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.pratilipi.connect4.model.Board;

@Service
public class GameService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private RedisTemplate<String, Board> redisTemplate;
    private HashOperations<String, String, Board> hashOperations; //to access Redis cache
    
    @Autowired
    public GameService(RedisTemplate<String, Board> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }
    
    
	public String add(String user, int col) {
		Board board = hashOperations.get("User", user);
		return board.add(col);
	}
	
	
	public String init() {
		String user = UUID.randomUUID().toString();
		hashOperations.put("User", user, new Board());
		return user;
	}
	
}
