package com.shopkeeper.model;

import com.shopkeeper.exception.ModelException;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghaojie
 * Date: 12-11-23
 * Time: 上午12:12
 */
public interface TopUpdate
{
    public Object updateFromTop(String topAccessToken) throws ModelException;
}
