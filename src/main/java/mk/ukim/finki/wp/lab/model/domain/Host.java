package mk.ukim.finki.wp.lab.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "hosts")
@Data
@NoArgsConstructor
public class Host extends BaseAuditableEntity{

    private String name;

    private String surname;

    @ManyToOne
    private Country country;

}
