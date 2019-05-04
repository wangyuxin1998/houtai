package bdqn.sshoa.service;

import bdqn.sshoa.entity.SysEmployee;

public interface EmployeeService {
	SysEmployee findEmployee(String sn);
	int findEmployeeLogins(String sn,String password);
	SysEmployee findEmployeeLogin(String sn,String password);
	SysEmployee findMannager(SysEmployee curEmp,String positionName);
}
