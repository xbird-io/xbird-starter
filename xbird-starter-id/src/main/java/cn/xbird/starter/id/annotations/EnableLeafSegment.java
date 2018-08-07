package cn.xbird.starter.id.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import cn.xbird.starter.id.boot.LeafSegmentConfiguration;

/**
 * @author zhycn
 * @since 2.2.0 2018-06-15
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LeafSegmentConfiguration.class)
public @interface EnableLeafSegment {

}
