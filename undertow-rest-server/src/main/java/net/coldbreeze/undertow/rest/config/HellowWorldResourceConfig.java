package net.coldbreeze.undertow.rest.config;

import net.coldbreeze.undertow.rest.resource.HelloWorldResource;
import org.glassfish.jersey.server.ResourceConfig;

public class HellowWorldResourceConfig extends ResourceConfig {
    public HellowWorldResourceConfig() {
        packages(HelloWorldResource.class.getPackage().getName());
    }
}
