package uos.uos25.headquarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uos.uos25.headquarter.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {}
