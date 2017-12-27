package entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
	
	private Integer id; //编号

	private Date careteTime;
	private Date updateTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
	public Date getCareteTime() {
		return careteTime;
	}
	public void setCareteTime(Date careteTime) {
		this.careteTime = careteTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
