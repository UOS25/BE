package uos.uos25.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uos.uos25.event.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
