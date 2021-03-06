package com.shopkeeper.service.request;

import com.rop.AbstractRopRequest;
import com.rop.annotation.AliasName;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghaojie
 * Date: 12-11-19
 * Time: 上午7:05
 */
public class ItemUpdateRequest extends AbstractRopRequest
{
    @NotNull
    @AliasName(name = "num_iid")
    private Long numIid;

    @AliasName(name = "group_id")
    private String groupId;

    @AliasName(name = "task_id")
    private String taskId;

    @AliasName(name = "time_onsale")
    private Date timeOnsale;

    @AliasName(name = "showcase_status")
    private String showcaseStatus;

    public Long getNumIid() {
        return numIid;
    }

    public void setNumIid(Long numIid) {
        this.numIid = numIid;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Date getTimeOnsale() {
        return timeOnsale;
    }

    public void setTimeOnsale(Date timeOnsale) {
        this.timeOnsale = timeOnsale;
    }

	public String getShowcaseStatus() {
		return showcaseStatus;
	}

	public void setShowcaseStatus(String showcaseStatus) {
		this.showcaseStatus = showcaseStatus;
	}
}
