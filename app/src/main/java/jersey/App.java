package jersey;

import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.EnumSet;

public class App {
    public static ServletContextHandler buildContextHandler() {
        // Create a ServletContextHandler with contextPath.
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        // todo: string or constant?
        context.setContextPath("/");

        // Add the Servlet implementing Jersey.
        //todo: string builder
        //todo: should path be a const instead of building it??
        ServletHolder servletHolder = context.addServlet(ServletContainer.class, "/" + AppConfig.VERSION_1 + "/*");
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter(AppConfig.JERSEY_PACKAGE, AppConfig.API_PATH);

        // Add the CrossOriginFilter to protect from CSRF attacks.
        FilterHolder filterHolder = context.addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        // Configure the filter.
        filterHolder.setAsyncSupported(true);

        return context;
    }

    public static void main(String[] args) {
        // Create a Server instance.
        Server server = new Server(AppConfig.PORT);

        // Link the context to the server.
        server.setHandler(buildContextHandler());

        try {
            server.start();
            server.join();
        } catch (Exception error) {
            //todo: error constructor
            System.out.printf("Error: %s", error);
        }
        finally {
            server.destroy();
        }
    }
}
