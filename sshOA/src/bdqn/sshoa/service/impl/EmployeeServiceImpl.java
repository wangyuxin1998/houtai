package bdqn.sshoa.service.impl;

import bdqn.sshoa.dao.EmployeeDao;
import bdqn.sshoa.entity.SysEmployee;
import bdqn.sshoa.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{
	EmployeeDao employeeDao=null;
	@Override
	public SysEmployee findEmployee(String sn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findEmployeeLogins(String sn, String password) {
		int ref=-1;
		SysEmployee emp= employeeDao.findEmployee(sn);
		if(emp!=null){
			if(password.equals(emp.getPassword())){
				ref=1;
			}
		}else{
			ref=0;
		}
		return ref;
	}
	@Override
	public SysEmployee findEmployeeLogin(String sn, String password) {
		// TODO Auto-generated method stub
		return employeeDao.findEmployeeLogin(sn, password);
	}
	
	@Override
	public SysEmployee findMannager(SysEmployee curEmp, String positionName) {
		// TODO Auto-generated method stub
		return employeeDao.findMannager(curEmp, positionName);
	}

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	
}
