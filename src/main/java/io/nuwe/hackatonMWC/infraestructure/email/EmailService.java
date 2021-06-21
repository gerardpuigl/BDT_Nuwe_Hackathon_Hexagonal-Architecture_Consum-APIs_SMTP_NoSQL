package io.nuwe.hackatonMWC.infraestructure.email;

import io.nuwe.hackatonMWC.application.dto.UserDTO;
import io.nuwe.hackatonMWC.domain.entities.User;

public interface EmailService {

	public void sendWelcomeEmail(User user);
	
	public void sendNotificationEmail(UserDTO user);

}
