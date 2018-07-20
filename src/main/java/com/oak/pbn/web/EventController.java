package com.oak.pbn.web;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sheigutn.pushbullet.Pushbullet;
import com.oak.pbn.service.FreemarkerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/events")
public class EventController {

	@Value("${pushbullet.api.token}")
	private String apiToken;

	@Value("${notification.link}")
	private String notificationLink;

	@Value("${ignored.event.types:}")
	private String[] ignoredEventTypes;

	@Autowired
	private FreemarkerService freemarkerService;

	@GetMapping
	public String check() {
		return "READY";
	}

	@PostMapping
	public String pushNotification(@RequestBody Map<String, Object> event) {
		String eventType = (String) ((Map<String, Object>) event.get("event")).get("type");
		if (!Arrays.asList(ignoredEventTypes).contains(eventType)) {
			Pushbullet pushbullet = new Pushbullet(apiToken);
			String body = freemarkerService.process("notification.ftl", event);
			pushbullet.pushLink("Traccar event", body, notificationLink);
			log.debug("Event sent: {}", event);
			return "SENT";
		}
		log.debug("Ignoring event: {}", event);
		return "IGNORE";
	}
}
