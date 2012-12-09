package com.shopkeeper.model;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.shopkeeper.MongoManager;
import com.shopkeeper.TopAccessor;
import com.shopkeeper.common.TopCometManager;
import com.shopkeeper.common.TradeTaskCometListener;
import com.shopkeeper.exception.ModelException;
import com.shopkeeper.exception.TopException;
import com.shopkeeper.service.domain.TradeTask;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghaojie
 * Date: 12-12-6
 * Time: 下午1:38
 */
public class TradeModel extends AbstractModel implements TopUpdate
{

	@Override
    public String getCollectionName() {
		return "sk_autoonsale_task";
    }

    private TradeTask getLastTradeTask(Long userId) {
        DB db = MongoManager.getDB("db_top");
        DBCollection collection1 = db.getCollection("sk_trade_task");
        DBObject query = new BasicDBObject("user_id", userId);
        DBObject order = new BasicDBObject("create", true);
        DBObject objTask = collection1.findOne(query, null, order);
	    return parse(objTask, TradeTask.class);
    }

    private boolean incrementCustomerPermit(String accessToken, String type, String topic, String status) {
        this.setAccessToken(accessToken);
        TopAccessor topAccessor = new TopAccessor(accessToken);
        UserModel userModel = new UserModel();
	    boolean permit = userModel.isSubscriptionPermit(this.getUserId(), topic, "TradeSuccess");
        try {
	        if (!permit) {
                permit = topAccessor.incrementCustomerPermit(type, topic, status);
		        if (!permit) {
			        logger.error("创建用户主动通知失败 user_id:" + userId);
		        }
	        }
            return permit;
        } catch (TopException e) {
			// todo
	        logger.info(e.getMsg());
        }
        return false;
    }

	// sk_trade_topats_task: task_id, created, user_id, status(new, done, download)
	// start_time, end_time
	@SuppressWarnings(value = "unchecked")
	public Map createTradeTask(Date startTime, Date endTime) {
		TopAccessor topAccessor = new TopAccessor(this.getAccessToken());
		Map objectMap = null;
		try {
			objectMap = topAccessor.createTradeSoldGetTask(startTime, endTime);
			if (objectMap != null) {
				DBCollection collection1 = db.getCollection("sk_trade_topats_task");
				objectMap = (Map)objectMap.get("task");
				objectMap.put("user_id", userId);
				objectMap.put("status", "new");
				objectMap.put("start_time", startTime);
				objectMap.put("end_time", endTime);
				DBObject query = new BasicDBObject("user_id", userId);
				DBObject update = new BasicDBObject(objectMap);
				collection1.update(query, update, true, false);
			}
		} catch (TopException e) {
			logger.info(e.getMsg());
		}
		return objectMap;
	}

    @Override
    public void updateFromTop(String topAccessToken) throws ModelException {
        this.setAccessToken(topAccessToken);
	    if (incrementCustomerPermit(topAccessToken, "notify", "trade", "all")) {
		    Long userId = this.getUserId();
		    TradeTask tradeTask = getLastTradeTask(userId);
		    Date now = new Date();
		    Calendar calendar = Calendar.getInstance();

		    if (tradeTask == null) {
				// 第一次为用户创建获取订单任务
			    calendar.setTime(now);
			    calendar.add(Calendar.DAY_OF_MONTH, -3);
			    Date endTime = calendar.getTime();
			    calendar.add(Calendar.MONTH, -1);
			    Date startTime = calendar.getTime();
			    Map task = this.createTradeTask(startTime, endTime);
			    if (task != null) {
			        TopCometManager topCometManager = TopCometManager.getInstance();
			        topCometManager.addNewStream(userId, new TradeTaskCometListener());
			    }
		    }
		    else {
			    // 创建更新用户订单任务， 开始时间为上次创建的任务的结束时间
			    // todo
		    }
	    }
	    else {
		    logger.info("开通用户通知失败， 参数：notify, trade, all");
	    }

/*
        else {
            // 创建任务获取全部交易信息
            Date now = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.add(Calendar.DAY_OF_MONTH, -3);
            Date endTime = calendar.getTime();
            calendar.add(Calendar.MONTH, -1);
            Date startTime = calendar.getTime();
            Map<String, Object> objectMap = topAccessor.createTradeSoldGetTask(startTime, endTime);
            if (objectMap != null) {
                DBCollection collection1 = db.getCollection("sk_trade_topats_task");
                objectMap = (Map<String, Object>)objectMap.get("task");
                objectMap.put("user_id", userId);
                DBObject query = new BasicDBObject("user_id", userId);
                DBObject update = new BasicDBObject(objectMap);
                collection1.update(query, update, true, false);
                TopCometManager topCometManager = TopCometManager.getInstance();
                topCometManager.addNewStream(userId, new TradeTaskCometListener());
            }
        }
        if (tradeTask != null) {
            //Date createdTime = tradeTask.getCreate();

        }
        else {

        }
        */
    }
}
