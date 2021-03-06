package me.bucklb.auditDemo.Config;
/*
    Sleuth seems a sticking point with jdk11.  Start again from the beginning

    Some decent stuff in:
        http://ryanjbaxter.com/cloud/spring%20cloud/spring/2016/07/07/spring-cloud-sleuth.html



 */


import brave.Tracing;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;
import brave.propagation.ThreadLocalCurrentTraceContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import brave.context.slf4j.MDCScopeDecorator;

/*
    Controls aspects of tracing such as the service name that shows up in the UI

    IF enable this to be detected as a bean then seems to do its own Zipkin style thing and at the same time interfere
    with the logging I'm trying to do (trace, span, parent tracking)

*/
//@Configuration
public class SleuthConfig {

    @Bean
    Tracing tracing(@Value("${spring.application.name}") String serviceName) {
        return Tracing.newBuilder()
                .localServiceName(serviceName)
                .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "user-name"))
                .currentTraceContext(ThreadLocalCurrentTraceContext.newBuilder()
                        .addScopeDecorator(MDCScopeDecorator.create()) // puts trace IDs into logs
                        .build()
                )
                .build();


//                .spanReporter(spanReporter()).build();
    }


}
