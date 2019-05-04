package bdqn.sshoa.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import bdqn.sshoa.entity.BizCheckResult;
import bdqn.sshoa.entity.BizClaimVoucher;
import bdqn.sshoa.util.ClaimCondition;
import bdqn.sshoa.util.PageTurn;

public interface BizClaimVoucherService {
	void addBizClaimVouche(BizClaimVoucher bizClaimVoucher);
	PageTurn<BizClaimVoucher> finBizClaimVoucher(ClaimCondition condition,  int PageIndex, int PageSize);
	public Map<String, String> finStatusMap();
	BizClaimVoucher findBizClaimVoucherId(Serializable id);
	void checkClaimVoucher(BizCheckResult bizCheckResult);
}
