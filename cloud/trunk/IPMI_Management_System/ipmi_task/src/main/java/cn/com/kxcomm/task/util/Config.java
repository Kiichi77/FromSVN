package cn.com.kxcomm.task.util;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;

public class Config {
	private static final Logger log = Logger.getLogger(Config.class);
	private static CompositeConfiguration config;
	private static Config instance;

	private Config() {
		try {
			config = new CompositeConfiguration();
			String propsFileName = getClass().getResource("/conf.properties").getFile();
			log.info("propsFileName:"+propsFileName);
			PropertiesConfiguration propsConfig = new PropertiesConfiguration();
			propsConfig.load(propsFileName);
			FileChangedReloadingStrategy propsReload = new FileChangedReloadingStrategy();
			propsReload.setRefreshDelay(600000L);
			propsConfig.setReloadingStrategy(propsReload);
			config.addConfiguration(propsConfig);
		} catch (Exception ex) {
			log.error("无法找到config.properties文件:", ex);
		} 
	}

	public static synchronized CompositeConfiguration getConfig() {
		if (instance == null)
			instance = new Config();
		return config;
	}

}