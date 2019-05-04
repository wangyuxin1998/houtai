package bdqn.sshoa.dao;

import bdqn.sshoa.entity.SysEmployee;

public interface EmployeeDao  {
	SysEmployee findEmployee(String sn);
	SysEmployee findEmployeeLogin(String sn,String password);
	SysEmployee findEmployeeSn(String sn);
	SysEmployee findMannager(SysEmployee curEmp,String positionName);
	
}
