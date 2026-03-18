package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host, Long> {
}
