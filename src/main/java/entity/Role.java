package entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role extends BaseEntity{
	private String roleName;//角色名称
    private String description; //角色描述,UI界面显示使用
    private Set<Resource> resources; //拥有的资源
    private Boolean available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户
    private Set<User> users;//所属用户
    private Set<Role> roles; //下级角色列表
    private Role role; //上级级角色
    private Organization organization; //所属机构
    

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@OneToMany
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ManyToOne
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
