package com.github.lburgazzoli.examples.karaf.axon;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;

/**
 *
 */
public class SimpleCommandGateway extends DefaultCommandGateway {

    /**
     * c-tor
     *
     * @param commandBus
     */
    public SimpleCommandGateway(CommandBus commandBus) {
        super(commandBus);
    }
}
