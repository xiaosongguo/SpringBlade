package org.springblade.system.strategy;

import org.springframework.stereotype.Component;

@Component
public class LtIsmgNameStrategy  extends BaseIsmgNameStrategy{

	private static final String prefix = "ltwn";

	@Override
	protected String getPrefix() {
		return prefix;
	}

}
