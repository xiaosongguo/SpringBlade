package org.springblade.system.strategy;

import org.springframework.stereotype.Component;

@Component
public class DxIsmgNameStrategy extends BaseIsmgNameStrategy{

	private static final String prefix = "dxwn";

	@Override
	protected String getPrefix() {
		return prefix;
	}

}
