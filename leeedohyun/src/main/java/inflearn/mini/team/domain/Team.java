package inflearn.mini.team.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import inflearn.mini.employee.domain.Employee;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name")
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Employee> employees = new ArrayList<>();

    public Team(final String name) {
        this.name = name;
    }

    public void addEmployee(final Employee employee) {
        employees.add(employee);
    }

    public int countEmployees() {
        return employees.size();
    }

    public String getManagerName() {
        final Employee manager = findManager();
        if (manager != null) {
            return manager.getName();
        }
        return null;
    }

    private Employee findManager() {
        return employees.stream()
                .filter(Employee::isManager)
                .findFirst()
                .orElse(null);
    }
}
