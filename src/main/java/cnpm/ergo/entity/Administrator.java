package cnpm.ergo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Administrator.findAll", query = "SELECT a FROM Administrator a")

public class Administrator extends User{

}
