package com.richard.wiki.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationRecord {
    /**
     * desc         描述
     * type         操作类型：1—add、2-update、3-delete、4-others
     */
    String desc() default "";
    String type() default "";
}
