package ru.hackeru.hibernate.Entity;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "EMPLOYEE_ID")
    int employee_id;

    String dep_name;

    public Department(){}


    public Department(int employee_id, String dep_name) {
        this.employee_id = employee_id;
        this.dep_name = dep_name;
    }


    public Department(String dep_name) {
        this.dep_name = dep_name;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public String getDep_name() {
        return dep_name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "employee_id=" + employee_id +
                ", dep_name='" + dep_name + '\'' +
                '}';
    }
}
