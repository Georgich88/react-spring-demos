package com.georgeisaev.oktatutorial.repositories;

import com.georgeisaev.oktatutorial.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

	Group findByName(String name);

}
