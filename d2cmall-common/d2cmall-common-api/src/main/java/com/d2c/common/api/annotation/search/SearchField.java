package com.d2c.common.api.annotation.search;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.d2c.common.api.query.enums.OperType;
import com.d2c.common.api.query.enums.ShowType;
import com.d2c.common.api.query.enums.SortType;
import com.d2c.common.api.query.enums.ValueType;

@Target({ FIELD })
@Retention(RUNTIME)
public @interface SearchField {
	
	String value() default "";

	String name() default "";
	
	String showName() default "";

	OperType oper() default OperType.EQUAL;

	ShowType show() default ShowType.TEXT;

	ValueType type() default ValueType.STRING;

	SortType sort() default SortType.DESC;
	
	SearchParam[] params() default {};

}
