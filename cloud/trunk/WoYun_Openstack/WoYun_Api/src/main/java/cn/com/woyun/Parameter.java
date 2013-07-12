package cn.com.woyun;

public class Parameter {
	public static String KEYSTONE_AUTH_URL = "http://192.168.1.50:5000/v2.0";
	public static String KEYSTONE_ADMIN_URL = "http://192.168.1.50:35357/v2.0";
	
	/**
	 * serviceCatalog的类型
	 */
	public static String FACTING_PUBLIC = "public";
	public static String FACTING_INTERNAL = "internal";
	public static String FACTING_ADMIN = "admin";
	
	/**
	 * 数据库在Creating Services的时候，对应数据库中type的类型
	 */
	public static String NOVA_TYPE_COMPUTE = "compute"; //nova对应的type
	public static String GLANCE_TYPE_IMAGE = "image";  //glance对应的type
	
	public static String REBOOT_TYPE = "SOFT"; //重启服务器的type类型
}
