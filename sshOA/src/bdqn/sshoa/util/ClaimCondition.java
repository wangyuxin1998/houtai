package bdqn.sshoa.util;

import java.util.Date;

import bdqn.sshoa.entity.SysEmployee;

public class ClaimCondition {
	private SysEmployee employee;
	private String  empSn;
	private String status;
	private Date startDate;
	private Date endDate;
	public SysEmployee getEmployee() {
		return employee;
	}
	public void setEmployee(SysEmployee employee) {
		this.employee = employee;
	}
	public String getEmpSn() {
		return empSn;
	}
	public void setEmpSn(String empSn) {
		this.empSn = empSn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
