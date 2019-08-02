package org.springblade.system.strategy;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.springblade.system.entity.ChannelResource;
import org.springblade.system.enums.OperatorTypeEnum;
import org.springblade.system.enums.SrcTypeEnum;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class IsmgNameContext{

	/**
	 * 第一个码号类型1：单网码号，2：三网合一
	 * 第二个运营商类型1：移动，2：联通，3：电信
	 */
	Table<SrcTypeEnum, OperatorTypeEnum, IsmgNameStrategy> strategyTable = HashBasedTable.create();

	public IsmgNameContext(Map<String, IsmgNameStrategy> strategyMap) {
		this.strategyTable.clear();
		strategyMap.forEach((k, v)-> {
			if (v instanceof YdIsmgNameStrategy){
				strategyTable.put(SrcTypeEnum.SINGLE,OperatorTypeEnum.YD,v);
			}else if (v instanceof LtIsmgNameStrategy){
				strategyTable.put(SrcTypeEnum.SINGLE,OperatorTypeEnum.LT,v);
			}else if (v instanceof DxIsmgNameStrategy){
				strategyTable.put(SrcTypeEnum.SINGLE,OperatorTypeEnum.DX,v);
			}else if (v instanceof YdLtDxIsmgNameStrategy){
				strategyTable.put(SrcTypeEnum.TRIPLE,OperatorTypeEnum.YDLTDX,v);
			}
		});
	}

	public String getIsmgName(ChannelResource resource) {
		return strategyTable.get(resource.getCodeType(),resource.getSupplierType()).getIsmgName(resource);
	}
}
