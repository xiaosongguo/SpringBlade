package org.springblade.system.strategy;

import org.springframework.stereotype.Component;

@Component
public class YdLtDxIsmgNameStrategy extends BaseIsmgNameStrategy{


	private static final String prefix = "ydltdxwn";

	@Override
	protected String getPrefix() {
		return prefix;
	}

}
