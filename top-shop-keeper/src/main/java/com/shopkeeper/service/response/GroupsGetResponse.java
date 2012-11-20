package com.shopkeeper.service.response;

import com.shopkeeper.service.domain.Group;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghaojie
 * Date: 12-11-18
 * Time: 下午8:59
 * To change this template use File | Settings | File Templates.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "groups_get_response")
public class GroupsGetResponse
{
    @XmlElement
    private List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}