package com.georgeisaev.oktatutorial.controllers;

import com.georgeisaev.oktatutorial.domain.Group;
import com.georgeisaev.oktatutorial.repositories.GroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class GroupController {

	private final GroupRepository groupRepository;

	@Autowired
	public GroupController(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@GetMapping("/groups")
	public Collection<Group> groups() {
		return groupRepository.findAll();
	}

	@GetMapping("/group/{id}")
	public ResponseEntity<?> getGroup(@PathVariable Long id) {
		Optional<Group> group = groupRepository.findById(id);
		return group.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/group")
	public ResponseEntity<Group> createGroup(@Valid @RequestBody Group group) throws URISyntaxException {
		log.info("Request to create group: {}", group);
		Group result = groupRepository.save(group);
		return ResponseEntity.created(new URI("/api/group/" + result.getId()))
				.body(result);
	}

	@PutMapping("/group/{id}")
	public ResponseEntity<Group> updateGroup(@Valid @RequestBody Group group) {
		log.info("Request to update group: {}", group);
		Group result = groupRepository.save(group);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping("/group/{id}")
	public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
		log.info("Request to delete group: {}", id);
		groupRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
