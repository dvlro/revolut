package io.nobel.myapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by petre on 10/31/2018.
 */
public class LoadConfigurationListener implements ServletContextListener {

    private static final Logger LOG = LoggerFactory.getLogger(LoadConfigurationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info(this.getClass().getName() + ":: contextInitialized ...");
		/*ManagementService.createDAOs();*/
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info(this.getClass().getName() + ":: contextDestroyed ...");

    }
}
