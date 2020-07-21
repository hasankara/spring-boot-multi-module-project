package hasan.kara.domain.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "users")
@Where(clause = "deleted = false")
public class User implements UserDetails, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6122191665047930366L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "email" , nullable = false)
	//@Email(message = "*Please provide a valid Email")
	//@NotEmpty(message = "*Please provide an email")
	private String email;

	@Column(name = "password")
	@NotEmpty(message = "*Please provide your password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "deleted")
	private Boolean deleted;
	
	@Column(name = "activationUUID")
	private String emailActivationUUID;

	@Column(name="CREATE_DATE", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date createTime;

	@Column(name="UPDATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss", timezone="UTC")
	private Date updateTime;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new LinkedHashSet<Role>();

	@Column(name = "pwd_reset_id")
	private String passwordResetID;
    
	@Column(name="pwd_reset_create_time")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss", timezone="UTC")
	private Date pwdResetCreateTime;
	
	public User() {
		super();
	}


	public User(Long id, String username, String email,
			@NotEmpty(message = "*Please provide your password") String password, String name, String lastName,
			Boolean enabled, Boolean active, Boolean deleted, String emailActivationUUID, Date createTime,
			Date updateTime, Set<Role> roles, String passwordResetID, Date pwdResetCreateTime) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.enabled = enabled;
		this.active = active;
		this.deleted = deleted;
		this.emailActivationUUID = emailActivationUUID;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.roles = roles;
		this.passwordResetID = passwordResetID;
		this.pwdResetCreateTime = pwdResetCreateTime;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getEmailActivationUUID() {
		return emailActivationUUID;
	}

	public void setEmailActivationUUID(String emailActivationUUID) {
		this.emailActivationUUID = emailActivationUUID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getPasswordResetID() {
		return passwordResetID;
	}

	public void setPasswordResetID(String passwordResetID) {
		this.passwordResetID = passwordResetID;
	}

	public Date getPwdResetCreateTime() {
		return pwdResetCreateTime;
	}

	public void setPwdResetCreateTime(Date pwdResetCreateTime) {
		this.pwdResetCreateTime = pwdResetCreateTime;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for (Role role : getRoles()) {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}