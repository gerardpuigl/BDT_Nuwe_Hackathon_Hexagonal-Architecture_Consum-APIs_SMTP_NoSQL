package io.nuwe.hackatonMWC.application.apis;

import java.util.InvalidPropertiesFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;;

@Component
public class ApiMailboxlayer {

	@Autowired
	WebClient webClient;

	@Value("${mailboxlayer.apikey}")
	private String apikey;

	public void checkEmail(String email) throws InvalidPropertiesFormatException {

		JsonNode apiAnswer = webClient.get()
				.uri("https://apilayer.net/api/check?access_key=" + apikey + "&email=" + email)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(JsonNode.class).block();

		if (!apiAnswer.findValue("format_valid").asBoolean())
			throw new InvalidPropertiesFormatException("The email: " + email + " hasn't valid format");
		if (!apiAnswer.findValue("mx_found").asBoolean())
			throw new InvalidPropertiesFormatException("The email: " + email + " domain don't exist");
	}
}
