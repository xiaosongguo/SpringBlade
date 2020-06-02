package org.springblade.system.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.ISMGSend;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ISMGSend对象", description = "网关发送")
public class ISMGSendVO extends ISMGSend {
	private static final long serialVersionUID = 1L;

	//开始和结束时间
	private  String beginDate ;
	private  String endDate;
	//通道id
	private String ismgId;
	//查询类型
	private Integer searchType;
}
