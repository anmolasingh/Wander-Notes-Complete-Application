package com.wander.notes.service.definition;

import java.util.List;

import com.wander.notes.dao.Note;
import com.wander.notes.model.AddNoteApiRequest;
import com.wander.notes.model.NoteApiResponse;
import com.wander.notes.model.UpdateNoteApiRequest;

public interface NotesService {

	int addNote(AddNoteApiRequest request)throws Exception;

	int updateNote(UpdateNoteApiRequest addNoteApiRequest)throws Exception;

	int deleteNote(String deleteNoteId)throws Exception;

	NoteApiResponse getNote(String id)throws Exception;

	List<Note> getUserNotes(String id)throws Exception;
}
