package cnpm.ergo.entity;


import jakarta.persistence.*;

@Entity
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")

public class Employee extends User{

}
