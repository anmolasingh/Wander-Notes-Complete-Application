package com.wander.notes.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {
	
	List<Note> findByUsername(String username);
}
