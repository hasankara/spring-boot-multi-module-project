package hasan.kara.domain.entity;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

	private static final long serialVersionUID = -7075213009315781457L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;
	
    @Column(name = "role")
    private String role;

	public Role() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
    public int hashCode() {
        return Objects.hash(this.role);
    }

    public boolean equals(Object o) {
        if (o == this) { return true; }
        if (o == null || !(o instanceof Role) ) { return false; }
        return Objects.equals(this.role, ((Role) o).role);
    }
    
}
