package entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User extends BaseEntity{
	
    private Organization organization; //所属机构
    private String username; //用户名
    private String password; //密码
    private String salt; //加密密码的盐
    private Role role; //所属角色
    private Boolean locked = Boolean.FALSE;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	
}
