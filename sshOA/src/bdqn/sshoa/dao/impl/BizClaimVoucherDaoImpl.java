package bdqn.sshoa.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bdqn.sshoa.dao.BizClaimVoucherDao;
import bdqn.sshoa.entity.BizClaimVoucher;
import bdqn.sshoa.entity.SysEmployee;
import bdqn.sshoa.util.ClaimCondition;
import bdqn.sshoa.util.Constrants;
import bdqn.sshoa.util.PageTurn;

public class BizClaimVoucherDaoImpl extends HibernateDaoSupport implements BizClaimVoucherDao {
	private BizClaimVoucherDao bizClaimVoucherDao;
	@Override
	public void addBizClaimVouche(BizClaimVoucher bizClaimVoucher) {
		this.getHibernateTemplate().save(bizClaimVoucher);
	}
	@Override
	public PageTurn<BizClaimVoucher> finBizClaimVoucher(
		final ClaimCondition condition, final int PageIndex,final int PageSize) {	
		StringBuffer sb=new StringBuffer();
	
		if(condition.getEmpSn()!=null){
			sb.append("  and (b.sysEmployeeByCreateSn.sn=:empSn or sysEmployeeByNextDealSn.sn=:empSn)");
		}
		if(condition.getStatus()!=null&&!("".equals(condition.getStatus()))){
			sb.append(" and b.status=:status");
		}
		if(condition.getStartDate()!=null){
			sb.append(" and b.modifyTime>=:startDate");
		}
		if(condition.getEndDate()!=null){
			sb.append(" and b.modifyTime<=:endDate");
		}
		final String hql="from BizClaimVoucher  b Where 1=1"+sb.toString();
		final String hqlCount="Select count(b) from BizClaimVoucher b where 1=1"+sb.toString();
		return this.getHibernateTemplate().execute(new HibernateCallback<PageTurn>() {

			@Override
			public PageTurn doInHibernate(Session session)
					throws HibernateException, SQLException {
				PageTurn<BizClaimVoucher>pageTurn=new PageTurn<BizClaimVoucher>();
				pageTurn.setPageIndex(PageIndex);
				pageTurn.setPageSize(PageSize);
				Query query=session.createQuery(hql);
				query.setProperties(condition);
				query.setFirstResult((PageIndex-1)*PageSize);
				query.setMaxResults(PageSize);
				pageTurn.setList(query.list());
				
				Query queryCount=session.createQuery(hqlCount);
				queryCount.setProperties(condition);
				int count=Integer.parseInt(queryCount.uniqueResult().toString()) ;
				pageTurn.setTotalCount(count);
				return pageTurn;
			}
		});
	}
	@Override
	public BizClaimVoucher findBizClaimVoucherId(Serializable id) {
		String hql="from BizClaimVoucher  b Where b.id=?";
		List<BizClaimVoucher> cherList=this.getHibernateTemplate().find(hql, id);
		BizClaimVoucher voucher=null;
		if(cherList.size()>0){
			voucher=cherList.get(0);
		}
		return voucher;
	}
	@Override
	public void updateBlizClaimVouche(BizClaimVoucher bizClaimVoucher) {
		this.getHibernateTemplate().update(bizClaimVoucher);
	}
	
}
