package io.nuwe.hackatonMWC.email;

import io.nuwe.hackatonMWC.domain.User;

public interface EmailService {

	public void sendWelcomeEmail(User user);

}
