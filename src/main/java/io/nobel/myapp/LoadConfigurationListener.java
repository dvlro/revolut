package io.nobel.myapp;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by petre on 10/31/2018.
 */
public class LoadConfigurationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(this.getClass().getName() + ":: contextInitialized ...");
		/*ManagementService.createDAOs();*/
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println(this.getClass().getName() + ":: contextDestroyed ...");

    }
}
