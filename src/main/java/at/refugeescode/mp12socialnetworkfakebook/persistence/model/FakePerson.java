package at.refugeescode.mp12socialnetworkfakebook.persistence.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FakePerson {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany (fetch = FetchType.EAGER)
    private List<FakePerson> friends = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FakePerson> getFriends() {
        return friends;
    }

    public FakePerson(String name, List<FakePerson> friends) {
        this.name = name;
        this.friends = friends;
    }

    public FakePerson() {
    }

    public void setFriends(List<FakePerson> friends) {
        this.friends = friends;


    }
}
