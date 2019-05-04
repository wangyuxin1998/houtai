package bdqn.sshoa.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bdqn.sshoa.dao.EmployeeDao;
import bdqn.sshoa.entity.SysEmployee;
import bdqn.sshoa.util.Constrants;

public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao{

	@Override
	public SysEmployee findEmployee(String sn) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(SysEmployee.class,sn);
	}
	
	//验证登录
	@Override
	public SysEmployee findEmployeeLogin(String sn, String password) {
		String hql="from SysEmployee e  where e.sn=? and e.password=? and e.status=?";
		List<SysEmployee>empList=this.getHibernateTemplate().find(hql, sn,password,Constrants.EMP_STATUS_ON);
		SysEmployee emp=null;
		if(empList.size()>0){
			emp=empList.get(0);
		}
		return emp;
	}
	//通过查找账号判断登录是否正确
	@Override
	public SysEmployee findEmployeeSn(String sn) {
		String hql="from SysEmployee e  where e.sn=?";
		List<SysEmployee>empList=this.getHibernateTemplate().find(hql, sn);
		SysEmployee emp=null;
		if(empList.size()>0){
			emp=empList.get(0);
		}
		return emp;
	}

	@Override
	public SysEmployee findMannager(SysEmployee curEmp,String positionName) {
		String hql="";
		List<SysEmployee>empList=null;
		if(curEmp!=null){
			hql="from SysEmployee e Where e.sysDepartment.id=? and e.position.nameCn=? ";
			empList=this.getHibernateTemplate().find(hql,curEmp.getSysDepartment().getId(),positionName);
		}else{
			hql="from SysEmployee e Where e.position.nameCn=?";
			empList=this.getHibernateTemplate().find(hql,positionName);
		}
		SysEmployee emp=null;
		if(empList.size()>0&&empList!=null){
			emp=empList.get(0);
		}
		return emp;
	}

}
