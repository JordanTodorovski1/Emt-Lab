package mk.ukim.finki.wp.lab.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.lab.model.enums.Category;
import mk.ukim.finki.wp.lab.model.enums.State;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "accommodations")
@Data
@NoArgsConstructor
@NamedEntityGraph(
        name = "accommodation-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "host", subgraph = "host-item-subgraph"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "host-item-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("country")
                        }
                )
        }
)

public class Accommodation extends BaseAuditableEntity{

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Host host;

    private Integer numRooms;

    public Accommodation(String name, Category category, State state, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.state = state;
        this.host = host;
        this.numRooms = numRooms;
    }
}
