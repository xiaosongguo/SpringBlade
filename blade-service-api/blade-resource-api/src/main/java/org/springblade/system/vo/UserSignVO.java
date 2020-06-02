package org.springblade.system.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.UserSign;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserSignVO对象", description = "用户签名")
public class UserSignVO extends UserSign {
	private static final long serialVersionUID = 1L;

	//以下是查询条件的封装

	//通道ids
	private List<Integer> ISMGIds;

	//签名名称（相似）
	private String signature;

	//签名名称（多选）
	private String signatures;

	//开始和结束时间
	private  String beginDate;
	private  String endDate;

	//总条数
	private Integer totalCnt;



}
