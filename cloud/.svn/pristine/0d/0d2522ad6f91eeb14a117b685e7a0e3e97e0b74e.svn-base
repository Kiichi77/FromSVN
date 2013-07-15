package cn.com.kxcomm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * 系统配置文件
 */
public class AppConfig {

	private static Logger log = Logger.getLogger(AppConfig.class);

	// 配置文件路径
	private String cfgFilePath = null;
	// 应用根目录
	private String rootPath = null;
	// classes目录
	private String classPath = null;
	private HashMap propertiesMap = new HashMap();
	private String cfgFileName = "config.properties";
	private static AppConfig config = new AppConfig();

	/**
	 * 构造函数，初始化根目录，并读配置文件
	 */
	private AppConfig() {
		classPath = AppConfig.class.getResource("/").getFile();
		cfgFilePath = classPath + cfgFileName;
		rootPath = classPath.replace("/WEB-INF/classes/", "");
		rootPath = rootPath.replace("%20", " ");
		log.info("classPath:" + classPath);
		log.info("rootPath:" + rootPath);
		try {
			readConfig();
		} catch (IOException e) {
			log.error("AppConfig init error." + e.getMessage(), e);
		}
	}

	/**
	 * 取唯一的cfg实例
	 * 
	 * @return 唯一的cfg实例
	 */
	public static synchronized AppConfig getInstance() {
		return config;
	}

	/**
	 * 工具方法,读取Properties配置文件
	 * 
	 * @param propPath
	 *            properties配置文件绝对路径
	 * @return Map
	 */
	public static Map loadProperties(String propPath) {
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(propPath));
			properties.load(inputStream);
		} catch (IOException e) {
			log.error("load properties error." + propPath, e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					log.warn(e.getMessage());
				}
			}
		}
		Map propMap = new HashMap();
		propMap.putAll(properties);
		return propMap;
	}

	/**
	 * 获取应用根目录（网站根目录,如d:\MyApp）
	 * 
	 * @return
	 */
	public String getRoot() {
		return rootPath;
	}

	/**
	 * 读取配置文件的路径
	 * 
	 * @return 配置文件的路径
	 */
	public String getCfgFilePath() {
		return cfgFilePath;
	}


	/**
	 * 从配置文件中读取必要的信息
	 * 
	 * @throws IOException
	 */
	public void readConfig() throws IOException {
		try {
			log.info("reading... config file:" + cfgFilePath);
			propertiesMap.clear();
			propertiesMap.putAll(loadProperties(cfgFilePath));
			log.info("config loaded.");
			//load system admin
//			String str = getString("system.admin");
//			log.info("system.admin:" + str);
//			if (str != null) {
//				String[] arr = str.split(",");
//				this.systemAdmin.clear();
//				this.systemAdmin.addAll(Arrays.asList(arr));
//			}
			//--
		} catch (Exception e) {
			log.error("config read fail.." + e.getMessage(), e);
		}
	}

	/**
	 * 根据关键字返回目录绝对路径
	 * 
	 * @param key
	 *            关键字
	 * @return String 属性值
	 */
	public String getRealPath(String key) {
		if (key == null)
			throw new IllegalArgumentException("key can't be null!");
		return getRoot() + getString(key);
	}

	/**
	 * 取WEB-INF绝对路径
	 * 
	 * @return WEB-INF绝对路径目录(eg:/X:/XXX//WEB-INF/)
	 */
	public String getWebInf() {
		if (classPath.indexOf("WEB-INF") != -1) {
			return getRoot() + "/WEB-INF/";
		} else {
			log.warn("no WEB-INF path in current environment. return the root path instead.");
			return getRoot();
		}
	}

	/**
	 * 取类的运行根目录的绝对路径
	 * 
	 * @return classes绝对路径目录(eg:在web环境中返回/X:/XXX//WEB-INF/classes/)
	 */
	public String getClassPath() {
		return classPath;
	}

	/**
	 * 根据关键字查询属性值
	 * 
	 * @param key
	 *            关键字
	 * @return String属性值
	 */
	public String getString(String key) {
		if (key == null)
			throw new IllegalArgumentException("key can't be null!");
		return (String) propertiesMap.get(key);
	}

	/**
	 * 根据关键字查询属性值
	 * 
	 * @param key
	 *            关键字
	 * @return int类型的属性值
	 */
	public int getInt(String key) {
		return Integer.parseInt(getString(key));
	}

	/**
	 * 根据关键字查询属性值
	 * 
	 * @param key
	 *            关键字
	 * @return long类型的属性值
	 */
	public long getLong(String key) {
		return Long.parseLong(getString(key));
	}

	/**
	 * 根据关键字查询属性值
	 * 
	 * @param key
	 *            关键字
	 * @return boolean类型的属性值
	 */
	public boolean getBoolean(String key) {
		return Boolean.parseBoolean(getString(key));
	}

	/**
	 * 根据关键字查询属性值
	 * 
	 * @param key
	 *            关键字
	 * @return double类型的属性值
	 */
	public double getDouble(String key) {
		return Double.parseDouble(getString(key));
	}

	/**
	 * 根据key和分隔符取属性值的字符串数组
	 * 
	 * @param key
	 * @param splitChar
	 * @return String[]
	 */
	public String[] getArray(String key, String splitChar) {
		String value = getString(key);
		String[] valuses = null;
		if (value != null && value.indexOf(splitChar) != -1) {
			valuses = value.split(splitChar);
		}
		return valuses;
	}
	
//	public Set<String> getSystemAdmin() {
//		return systemAdmin;
//	}

	public static void main(String[] args) {		
		log.info(AppConfig.getInstance().getString("dataSource.jdbcUrl"));
		log.info(AppConfig.getInstance().getRoot());
		log.info(AppConfig.getInstance().getWebInf());
		log.info(AppConfig.getInstance().getCfgFilePath());
		log.info(AppConfig.getInstance().getString("dataSource.jdbcUrl"));
	}

}
