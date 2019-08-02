package org.springblade.system.enums;

public enum RedisValueType {
	CM_MSISDN(0),PM_MSISDN_DELV(0),
	ORDER_SUBMIT_INFO(1),
	CM_SIGN_TD(2),
	CM_ISMG_BALCK(3),
	ISMG_SEND_LIMIT(4),
	ISMG_SIGN_SRC(5),
	ISMG_SIGN_EXTEND(6),
	MSISDN_STATUS(7),
	CM_USER_BLACK(8),
	CM_SIGN_BLACK(9),
	APLLIACAITON(10),
	;
	private int dbBase;
	private RedisValueType(int dbBase){
		this.dbBase = dbBase;
	}
	public int getDbBase() {
		return dbBase;
	}
}
