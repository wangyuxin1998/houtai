package bdqn.sshoa.test;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bdqn.sshoa.dao.EmployeeDao;
import bdqn.sshoa.entity.BizClaimVoucher;
import bdqn.sshoa.entity.SysEmployee;
import bdqn.sshoa.service.BizClaimVoucherService;
import bdqn.sshoa.service.EmployeeService;

public class testSSh {
	@Test
   public void testMethod1(){
	   ApplicationContext act=new ClassPathXmlApplicationContext("applicationContext.xml");
	   SessionFactory fac=(SessionFactory) act.getBean("sessionFactory");
	   Session session=fac.getCurrentSession();
	   Transaction  tx=session.beginTransaction();
	   SysEmployee  emp=  (SysEmployee) session.get(SysEmployee.class, "001");
	   System.out.println(emp.getName());
   }
	@Test
	   public void testMethod2(){
		   ApplicationContext act=new ClassPathXmlApplicationContext("applicationContext.xml");
		 EmployeeDao employeeDao=  (EmployeeDao) act.getBean("employeeDao");
		 SysEmployee  emp=  employeeDao.findEmployee("001");
		 System.out.println(emp.getName());
	   }
	@Test
	   public void testMethod3(){
		   ApplicationContext act=new ClassPathXmlApplicationContext("applicationContext.xml");
		   EmployeeService employeeService =(EmployeeService) act.getBean("employeeService");
		 SysEmployee  emp=  employeeService.findEmployeeLogin("001", "123");
		 System.out.println(emp.getName()+"---"+emp.getPosition().getNameEn());
	   }
	@Test
	   public void testMethod4(){
		   ApplicationContext act=new ClassPathXmlApplicationContext("applicationContext.xml");
		   BizClaimVoucherService bizClaimVoucherService=(BizClaimVoucherService) act.getBean("bizClaimVoucherService");
		  BizClaimVoucher bizClaimVoucher=bizClaimVoucherService.findBizClaimVoucherId(5);
		 System.out.println(bizClaimVoucher.getSysEmployeeByCreateSn().getName());
	   }
}
