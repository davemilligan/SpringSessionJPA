/*
MIT License

Copyright (c) [2017] [ukiuni]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.ukiuni.spring.session.jpa;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.session.MapSession;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

/**
 * Add this annotation to an {@code @Configuration} class to expose the
 * SessionRepositoryFilter as a bean named "springSessionRepositoryFilter" and
 * backed by a relational database with Spring Data JPA.
 *
 * <pre class="code">
 * &#064;Configuration
 * &#064;EnableJPAHttpSession
 * public class JPAHttpSessionConfig {
 *
 * }
 * </pre>
 *
 * For more information, see
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
 * 
 * @author ukiuni
 * @since 1.0.0
 * @see EnableSpringHttpSession
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(JPASessionConfiguration.class)
@Documented
public @interface EnableJPAHttpSession {

	/**
	 * The session timeout in seconds. By default, it is set to 1800 seconds (30
	 * minutes). This should be a non-negative integer.
	 *
	 * @return the seconds a session can be inactive before expiring
	 */
	int maxInactiveIntervalInSeconds() default MapSession.DEFAULT_MAX_INACTIVE_INTERVAL_SECONDS;
}
