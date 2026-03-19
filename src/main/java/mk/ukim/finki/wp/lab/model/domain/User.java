package mk.ukim.finki.wp.lab.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseAuditableEntity {

    private String username;
    private String name;
    private String surname;

    @ManyToMany(mappedBy = "users")
    private List<Reservation> reservations = new ArrayList<>();

    public User(String username, String name, String surname) {
        this.username = username;
        this.name = name;
        this.surname = surname;
    }
}