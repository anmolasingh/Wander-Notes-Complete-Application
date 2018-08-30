package com.wander.notes.service.definition;

import com.wander.notes.model.RegistrationApiRequest;

public interface RegisterService {
	
	int registerUser(RegistrationApiRequest request)throws Exception;
}
