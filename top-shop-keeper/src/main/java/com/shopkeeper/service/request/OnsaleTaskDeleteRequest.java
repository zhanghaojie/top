package com.shopkeeper.service.request;

import com.rop.AbstractRopRequest;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghaojie
 * Date: 12-11-20
 * Time: 上午6:52
 */
public class OnsaleTaskDeleteRequest extends AbstractRopRequest
{
    private String id;

    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}