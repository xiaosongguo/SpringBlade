package org.springblade.system.strategy;

import org.apache.commons.lang3.StringUtils;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.system.entity.ChannelResource;
import org.springblade.system.entity.DicArea;
import org.springblade.system.service.IDicAreaService;
import org.springblade.system.service.IIsmgService;

public abstract class BaseIsmgNameStrategy implements IsmgNameStrategy{

	protected ChannelResource resource;

	private static final String SW = "sw";
	private static final String UNDERLINE = "_";


	@Override
	public String getIsmgName(ChannelResource resource) {
		this.resource = resource ;
		StringBuffer sb = condition();
		sb.append(getSerialNum());
		sb.append(UNDERLINE);
		sb.append(StringUtils.right(Func.toStr(resource.getAccessNumber()),4));
		return sb.toString();
	}

	protected abstract  String getPrefix();

	private StringBuffer condition(){
		StringBuffer sb = new StringBuffer();
		sb.append(getPrefix());
		String provinceName = resource.getProvinceName();
		if(!Func.equalsSafe(provinceName,"全国")){
			sb.append(SW);
		}
		sb.append(UNDERLINE);
		sb.append(getAreaCode());
		return sb;
	}

	protected String getAreaCode(){
		if(Func.equalsSafe(resource.getProvinceName(),"全国")){
			return "0000";
		}
		DicArea area = new DicArea();
		area.setProvince(resource.getProvinceName());
		return  SpringUtil.getBean(IDicAreaService.class).getAreaCode(area);
	};

	protected  String getSerialNum() {
		StringBuffer condition = condition();
		return SpringUtil.getBean(IIsmgService.class).getSerialNum(condition.toString());
	}

}
