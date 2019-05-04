package bdqn.sshoa.dao.impl;

import java.io.Serializable;

import org.hibernate.FlushMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bdqn.sshoa.dao.BizCheckResultDao;
import bdqn.sshoa.dao.BizClaimVoucherDao;
import bdqn.sshoa.entity.BizCheckResult;
import bdqn.sshoa.entity.BizClaimVoucher;
import bdqn.sshoa.util.ClaimCondition;
import bdqn.sshoa.util.PageTurn;

public class BizCheckResultDaoImpl extends HibernateDaoSupport implements
		BizCheckResultDao {

	@Override
	public void addBizCheckResult(BizCheckResult checkResult) {
		this.getHibernateTemplate().save(checkResult);
		
	}


}
