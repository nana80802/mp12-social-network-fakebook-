package at.refugeescode.mp12socialnetworkfakebook.endpoint;

import at.refugeescode.mp12socialnetworkfakebook.persistence.model.FakePerson;
import at.refugeescode.mp12socialnetworkfakebook.persistence.repository.FakePersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class FakePersonEndPoint {
    private FakePersonRepository fakePersonRepository;

    public FakePersonEndPoint(FakePersonRepository fakePersonRepository) {
        this.fakePersonRepository = fakePersonRepository;
    }

    @GetMapping
    List<FakePerson> showAll(){
        return fakePersonRepository.findAll();
    }

    @PostMapping
    FakePerson addNew(@RequestBody FakePerson fakePerson){
        return fakePersonRepository.save(fakePerson);
    }

    @PutMapping("/{id1}/friend/{id2}")
    FakePerson addFriend(@PathVariable Long id1, @PathVariable Long id2) {
        Optional<FakePerson> fakePerson1 = fakePersonRepository.findById(id1);
        Optional<FakePerson> fakePerson2 = fakePersonRepository.findById(id2);
        if (fakePerson1.isPresent() && fakePerson2.isPresent())
            return addNewFriend(fakePerson1, fakePerson2);
        return null;
    }

    @PutMapping("/{id1}/unfriend/{id2}")
        FakePerson unfriend(@PathVariable Long id1, @PathVariable Long id2){
            Optional<FakePerson> fakePerson1 = fakePersonRepository.findById(id1);
            Optional<FakePerson> fakePerson2 = fakePersonRepository.findById(id2);
            if (fakePerson1.isPresent()&& fakePerson2.isPresent())
                return removeFriend(fakePerson1, fakePerson2);
            return null;
    }

    private FakePerson addNewFriend(Optional<FakePerson> fakePerson1, Optional<FakePerson> fakePerson2) {
        FakePerson person1 = fakePerson1.get();
        List<FakePerson> fakeFriends = person1.getFriends();
        fakeFriends.add(fakePerson2.get());
        person1.setFriends(fakeFriends);
        return fakePersonRepository.save(person1);
    }

    private FakePerson removeFriend(Optional<FakePerson> fakePerson1, Optional<FakePerson> fakePerson2) {
        FakePerson person1 = fakePerson1.get();
        List<FakePerson> fakeFriends = person1.getFriends();
        fakeFriends.remove(fakePerson2.get());
        person1.setFriends(fakeFriends);
        return fakePersonRepository.save(person1);
    }


}
