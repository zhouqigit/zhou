package entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 机构表
 * @author zhouqi
 *
 */
@Entity
@Table(name="organization")
public class Organization extends BaseEntity{
    private String name; //组织机构名称
    private String parentIds; //父编号列表，如1/2/
    private Boolean available = Boolean.FALSE;
    private Set<User> users;//所属用户
    private Set<Role> roles;//角色列表
    private Set<Organization> organizations; //下级机构列表
    private Organization parentOrganization;//上级机构


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@OneToMany
	public Set<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Set<Organization> organizations) {
		this.organizations = organizations;
	}

	@ManyToOne
	public Organization getParentOrganization() {
		return parentOrganization;
	}

	public void setParentOrganization(Organization parentOrganization) {
		this.parentOrganization = parentOrganization;
	}

    
}
