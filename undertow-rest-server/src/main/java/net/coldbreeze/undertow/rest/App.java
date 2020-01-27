package net.coldbreeze.undertow.rest;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.ListenerRegistry;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import net.coldbreeze.undertow.rest.config.HellowWorldResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.ServletException;
import javax.ws.rs.core.Application;

import static io.undertow.servlet.Servlets.listener;
import static io.undertow.servlet.Servlets.servlet;

public class App
{
    public static void main( String[] args ) throws ServletException {
        DeploymentInfo servletBuilder = Servlets.deployment();

        servletBuilder
                .setClassLoader(Application.class.getClassLoader())
                .setContextPath("/")
                .setDeploymentName("mss.war")
                .addServlets(servlet("jerseyServlet", ServletContainer.class)
                        .setLoadOnStartup(1)
                        .addInitParam("javax.ws.rs.Application", HellowWorldResourceConfig.class.getName())
                        .addMapping("/*"));

        DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
        manager.deploy();
        PathHandler path = Handlers.path(Handlers.redirect("/"))
                .addPrefixPath("/", manager.start());

        Undertow server =
                Undertow
                        .builder()
                        .addHttpListener(8080, "localhost")
                        .setHandler(path)
                        .build();

        server.start();
    }
}
