package org.eagle.chain.data.rocks.annotion;

import java.lang.annotation.*;

/**
 * @author ApacheMoy
 * @version create time：2023/1/10 16:49
 * @describe: for db name
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Database {
    /**
     * db name
     */
    public String name() default "empty";
}