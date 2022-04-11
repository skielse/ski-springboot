package com.ski.bootstart.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * @author wangzijie
 */
public class LogFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent iLoggingEvent) {
        if (iLoggingEvent.getLevel().isGreaterOrEqual(Level.ERROR)) {
            System.out.println("+++++++++i get one error++++++++"+iLoggingEvent.getMessage());
        }
        return FilterReply.NEUTRAL;
    }
}
