package org.springblade.system.strategy;

import org.springframework.stereotype.Component;

@Component
public class YdIsmgNameStrategy extends BaseIsmgNameStrategy{

	private static final String prefix = "ydwn";

	@Override
	protected String getPrefix() {
		return prefix;
	}

}
