package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "divisions")
@NamedQueries({
    @NamedQuery(
            name = "getAllDivisions",
            query = "SELECT dv FROM Department AS dv ORDER BY dv.id DESC"
            ),
    @NamedQuery(
            name = "getDivisionsCount",
            query = "SELECT COUNT(dv) FROM Department AS dv"
            ),

})
@Entity
public class Division {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;


    @Column(name = "division_name", nullable = false, unique = true)
    private String division_name;


    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDepartment() {
        return division_name;
    }

    public void setDepartment(String department) {
        this.division_name = department;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }


}
