package org.springblade.system.converter;

import org.springblade.system.enums.IEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class ValuedEnumConverter implements ConverterFactory<String, IEnum> {

	@Override
	public <T extends IEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new IntegerToEnum(targetType);
	}
	
	private class IntegerToEnum<T extends IEnum> implements Converter<String, T> {

        private final T[] values;

        public IntegerToEnum(Class<T> targetType) {
            values = targetType.getEnumConstants();
        }

        @Override
        public T convert(String source) {
            for (T t : values) {
                if (t.getValue() == Integer.valueOf(source)) {
                    return t;
                }
            }
            return null;
        }
    }
	
}
