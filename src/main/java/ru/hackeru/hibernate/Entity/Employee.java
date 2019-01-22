package ru.hackeru.hibernate.Entity;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;

    String name;

    int salary;

    int age;

    @OneToOne
    @JoinColumn(name="ADDRESS_ID")
    Address address;
    @OneToOne
    @JoinColumn(name="EMPLOYEE_ID")
    Department department;


    public Employee(){}

    public Employee(String name, int salary, int age, Address address, Department department) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.address = address;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", address=" + address +
                ", department=" + department +
                '}';
    }
}
