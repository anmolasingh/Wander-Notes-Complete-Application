package com.wander.notes.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wander.notes.dao.Note;
import com.wander.notes.dao.NoteRepository;
import com.wander.notes.model.AddNoteApiRequest;
import com.wander.notes.model.NoteApiResponse;
import com.wander.notes.model.UpdateNoteApiRequest;
import com.wander.notes.service.definition.NotesService;

@Service
public class NotesServiceImpl implements NotesService {

	@Autowired
	private NoteRepository noteDao;
	
	@Override
	public int addNote(AddNoteApiRequest request) throws Exception {
		Note note = new Note(request.getUsername(),request.getTitle(),request.getDescription(),Calendar.getInstance().getTime(),Calendar.getInstance().getTime());
		noteDao.save(note);
		return 1;
	}

	@Override
	public int updateNote(UpdateNoteApiRequest request) throws Exception {
		Optional<Note> previousNoteOptional = noteDao.findById(request.getId());
		if(!previousNoteOptional.isPresent()) {
			return -2;//Note not present
		}
		Note previousNote = previousNoteOptional.get();
		Note note = new Note(request.getId(),request.getUsername(),request.getTitle(),request.getDescription(),previousNote.getCreationTime(),Calendar.getInstance().getTime());
		noteDao.save(note);
		return 1;//Successful
	}

	@Override
	public int deleteNote(String deleteNoteId) throws Exception{
		Optional<Note> previousNoteOptional = noteDao.findById(deleteNoteId);
		if(!previousNoteOptional.isPresent()) {
			return -2;//Note not present
		}
		noteDao.delete(previousNoteOptional.get());
		return 1;
	}

	@Override
	public NoteApiResponse getNote(String id)throws Exception {
		NoteApiResponse apiResponse = null;
		Optional<Note> noteOptional = noteDao.findById(id);
		if(noteOptional.isPresent()) {
			apiResponse = new NoteApiResponse();
			Note note = noteOptional.get();
			apiResponse.setId(note.getId());
			apiResponse.setTitle(note.getTitle());
			apiResponse.setDescription(note.getDescription());
			apiResponse.setUsername(note.getUsername());
			apiResponse.setCreationDate(note.getCreationTime());
			apiResponse.setLastUpdateDate(note.getUpdateTime());
		}
		return apiResponse;
	}

	@Override
	public List<Note> getUserNotes(String id)throws Exception {
		
		return noteDao.findByUsername(id);
	}

}
