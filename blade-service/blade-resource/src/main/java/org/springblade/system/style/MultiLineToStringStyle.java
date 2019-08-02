package org.springblade.system.style;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MultiLineToStringStyle extends ToStringStyle {

	public static final ToStringStyle MULTI_LINE_STYLE = new MultiLineToStringStyle();


	private static final long serialVersionUID = 1L;

        MultiLineToStringStyle() {
            super();
			this.setUseClassName(false);
			this.setUseIdentityHashCode(false);
			this.setContentStart(StringUtils.EMPTY);
            this.setFieldSeparator(System.lineSeparator());
            this.setFieldSeparatorAtStart(false);
			this.setContentEnd(StringUtils.EMPTY);
        }

        private Object readResolve() {
            return MULTI_LINE_STYLE;
        }

    }
