package io.nuwe.hackatonMWC.infraestructure.email;

import io.nuwe.hackatonMWC.domain.entities.User;
import io.nuwe.hackatonMWC.infraestructure.dto.UserDTO;

public interface EmailService {

	public void sendWelcomeEmail(User user);
	
	public void sendNotificationEmail(UserDTO user);

}
