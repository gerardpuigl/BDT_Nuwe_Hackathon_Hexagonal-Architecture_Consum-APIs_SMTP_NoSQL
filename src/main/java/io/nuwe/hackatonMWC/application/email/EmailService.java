package io.nuwe.hackatonMWC.application.email;

import io.nuwe.hackatonMWC.domain.entities.User;

public interface EmailService {

	public void sendWelcomeEmail(User user);

}
