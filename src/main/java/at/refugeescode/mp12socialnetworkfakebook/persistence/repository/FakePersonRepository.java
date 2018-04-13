package at.refugeescode.mp12socialnetworkfakebook.persistence.repository;

import at.refugeescode.mp12socialnetworkfakebook.persistence.model.FakePerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FakePersonRepository extends JpaRepository<FakePerson, Long> {
}
