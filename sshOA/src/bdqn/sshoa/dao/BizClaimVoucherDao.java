package bdqn.sshoa.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import bdqn.sshoa.entity.BizClaimVoucher;
import bdqn.sshoa.util.ClaimCondition;
import bdqn.sshoa.util.Constrants;
import bdqn.sshoa.util.PageTurn;


/**
 * ±¨Ïúµ¥
 * @author Administrator
 *
 */
public interface BizClaimVoucherDao {
	void addBizClaimVouche(BizClaimVoucher bizClaimVoucher);
	PageTurn<BizClaimVoucher>finBizClaimVoucher(ClaimCondition condition,int PageIndex,int PageSize);
	BizClaimVoucher findBizClaimVoucherId(Serializable id);
	void updateBlizClaimVouche(BizClaimVoucher bizClaimVoucher);
}
