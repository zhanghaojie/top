package com.shopkeeper.service.response;

import com.shopkeeper.service.domain.Group;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghaojie
 * Date: 12-11-18
 * Time: 上午6:51
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "group_get_response")
public class GroupGetResponse
{
    @XmlElement
    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
