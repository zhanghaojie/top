package com.shopkeeper.service.request;

import com.rop.AbstractRopRequest;
import com.rop.annotation.AliasName;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghaojie
 * Date: 12-11-22
 * Time: 上午8:36
 */
public class ShowcaseUpdateRequest extends AbstractRopRequest
{
	@AliasName(name = "user_id")
	private Long userId;
    @AliasName(name = "is_used_include")
    private Boolean usedInclude;
    @AliasName(name = "is_used_exclude")
    private Boolean usedExclude;
    @AliasName(name = "is_used_limit_discount")
    private Boolean usedLimitDiscount;
    @AliasName(name = "is_launch")
    private Boolean isLaunch;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

    public Boolean getUsedInclude() {
        return usedInclude;
    }

    public void setUsedInclude(Boolean usedInclude) {
        this.usedInclude = usedInclude;
    }

    public Boolean getUsedExclude() {
        return usedExclude;
    }

    public void setUsedExclude(Boolean usedExclude) {
        this.usedExclude = usedExclude;
    }

    public Boolean getUsedLimitDiscount() {
        return usedLimitDiscount;
    }

    public void setUsedLimitDiscount(Boolean usedLimitDiscount) {
        this.usedLimitDiscount = usedLimitDiscount;
    }

    public Boolean getLaunch() {
        return isLaunch;
    }

    public void setLaunch(Boolean launch) {
        isLaunch = launch;
    }
}
