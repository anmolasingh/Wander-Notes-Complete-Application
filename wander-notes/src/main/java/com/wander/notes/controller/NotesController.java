package com.wander.notes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wander.notes.dao.Note;
import com.wander.notes.model.AddNoteApiRequest;
import com.wander.notes.model.NoteApiResponse;
import com.wander.notes.model.UpdateNoteApiRequest;
import com.wander.notes.service.definition.NotesService;

@CrossOrigin
@RestController
public class NotesController {
	
	@Autowired
	private NotesService notesService;
	
	@RequestMapping(value = "notes/add", method = RequestMethod.POST)
    public ResponseEntity<?> addNote(@RequestBody AddNoteApiRequest addNoteApiRequest, @Value("#{request.getAttribute('tokenUsername')}") String tokenUsername) {
		try {
			System.out.println("tokenUsername:" + tokenUsername);
			if(addNoteApiRequest == null || addNoteApiRequest.getUsername() == null || addNoteApiRequest.getUsername().trim().equals("")
    				|| addNoteApiRequest.getTitle() == null || addNoteApiRequest.getTitle().trim().equals("")
    				|| addNoteApiRequest.getDescription() == null || addNoteApiRequest.getDescription().trim().equals("")
    				|| !addNoteApiRequest.getUsername().equals(tokenUsername)) {
    			System.out.println("addNoteApiRequest:" + addNoteApiRequest);
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please form request correctly");
    		}
			
			
    		int status = notesService.addNote(addNoteApiRequest);
			return ResponseEntity.status(HttpStatus.OK).body(status);
		}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    	}
	}
	
	@RequestMapping(value = "notes/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateNote(@RequestBody UpdateNoteApiRequest updateNoteApiRequest, @Value("#{request.getAttribute('tokenUsername')}") String tokenUsername) {
		try {
			System.out.println("tokenUsername:" + tokenUsername);
			if(updateNoteApiRequest == null || updateNoteApiRequest.getUsername() == null || updateNoteApiRequest.getUsername().trim().equals("")
					|| updateNoteApiRequest.getId() == null || updateNoteApiRequest.getId().trim().equals("")
    				|| updateNoteApiRequest.getTitle() == null || updateNoteApiRequest.getTitle().trim().equals("")
    				|| updateNoteApiRequest.getDescription() == null || updateNoteApiRequest.getDescription().trim().equals("")
    				|| !updateNoteApiRequest.getUsername().equals(tokenUsername)) {
    			System.out.println("updateNoteApiRequest:" + updateNoteApiRequest);
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please form request correctly");
    		}
    		int status = notesService.updateNote(updateNoteApiRequest);
			return ResponseEntity.status(HttpStatus.OK).body(status);
		}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    	}
	}
	
	@RequestMapping(value = "notes/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteNote(@PathVariable String id) {
		try {
			System.out.println("deleteNoteId:" + id);
			if(id == null || id.trim().equals("")) {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please form request correctly");
    		}
			
    		int status = notesService.deleteNote(id);
			return ResponseEntity.status(HttpStatus.OK).body(status);
		}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    	}
	}
	
	@RequestMapping(value = "notes/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getNote(@PathVariable String id) {
		try {
			System.out.println("id:" + id);
			if(id == null || id.trim().equals("")) {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please form request correctly");
    		}
			
			NoteApiResponse response = notesService.getNote(id);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    	}
	}
	
	@RequestMapping(value = "notes/getlist/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserNotes(@PathVariable String id, @Value("#{request.getAttribute('tokenUsername')}") String tokenUsername) {
		try {
			System.out.println("id:" + id);
			System.out.println("tokenUsername:" + tokenUsername);
			if(id == null || id.trim().equals("") || !id.equals(tokenUsername)) {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please form request correctly");
    		}
			
			List<Note> response = notesService.getUserNotes(id);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    	}
	}
}
