package cnpm.ergo.entity;

import jakarta.persistence.*;

@Entity
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")

public class Customer extends User{

}
