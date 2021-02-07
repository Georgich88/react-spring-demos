package com.georgeisaev.oktatutorial.components;

import com.georgeisaev.oktatutorial.domain.Event;
import com.georgeisaev.oktatutorial.domain.Group;
import com.georgeisaev.oktatutorial.repositories.GroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Slf4j
@Component
public class Initializer implements CommandLineRunner {

	private final GroupRepository groupRepository;

	@Autowired
	public Initializer(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Stream.of("Denver JUG", "Utah JUG", "Seattle JUG",
				"Richmond JUG").forEach(name ->
				groupRepository.save(new Group(name))
		);
		Group djug = groupRepository.findByName("Denver JUG");
		Event e = Event.builder().title("Full Stack Reactive")
				.description("Reactive with Spring Boot + React")
				.date(Instant.parse("2018-12-12T18:00:00.000Z"))
				.build();
		djug.setEvents(Collections.singleton(e));
		groupRepository.save(djug);
		groupRepository.findAll().forEach(group -> log.info(group.toString()));
	}

}
