package mk.ukim.finki.wp.lab.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "countries")
@Data
@NoArgsConstructor
public class Country extends BaseEntity{

    private String name;

    private String continent;
}
