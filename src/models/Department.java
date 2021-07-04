package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "departments")
@NamedQueries({
    @NamedQuery(
            name = "getAllDepartments",
            query = "SELECT dp FROM Department AS dp ORDER BY dp.id DESC"
            ),
    @NamedQuery(
            name = "getDepartmentsCount",
            query = "SELECT COUNT(dp) FROM Department AS dp"
            ),

})
@Entity
public class Department {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @JoinColumn(name = "employee_id", nullable = false)
//    private Employee employee;

    @Column(name = "name", nullable = false, unique = true)
    private String name;


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

////    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }

    public String getDepartment() {
        return name;
    }

    public void setDepartment(String department) {
        this.name = department;
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
