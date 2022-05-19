package users_service.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @Column(name = "color", nullable = false)
    private String color;

    public Team() {
    }

    public Team(String teamName, String color) {
        this.teamName = teamName;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id && teamName.equals(team.teamName) && color.equals(team.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamName, color);
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                '}';
    }
}
