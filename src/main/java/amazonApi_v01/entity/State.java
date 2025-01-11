package amazonApi_v01.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "states")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 5)
    private String stateName;
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    public State() {
    }

    public State(Integer id, String stateName, Country country) {
        this.id = id;
        this.stateName = stateName;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
