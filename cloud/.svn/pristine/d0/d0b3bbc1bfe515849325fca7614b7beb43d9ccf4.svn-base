package cn.com.kxcomm.memcached;

/**缓存接口(所有缓存均必须实现该接口)
 * @author lydawen 2009-11-27
 * 
 * 
 */
public interface ICacheClient {

	/**
	 * 添加一个值,如果存在则失败
	 * @param key
	 * @param value
	 * @return 如果存在则false
	 */
	boolean add(String key,Object value);
	/**
	 * 添加一个值,如果存在则失败
	 * @param key
	 * @param value
	 * @param expiry 过期时间(毫秒)
	 * @return
	 */
	
	boolean add(String key,Object value,long expiry);
	/**
	 * 添加一个值,如果存在则失败
	 * @param key
	 * @param value
	 * @param expiry 过期时间(毫秒)
	 * @param hashCode 集群时用于负载权重
	 * @return
	 */
	boolean add(String key,Object value,long expiry,Integer hashCode);
	/**
	 * 添加一个值,如果存在则失败
	 * @param key
	 * @param value
	 * @param hashCode 集群时用于负载权重
	 * @return
	 */
	boolean add(String key,Object value,Integer hashCode);
	
	/**
	 * 删除一个值
	 * @param key
	 * @return
	 */
	boolean delete(String key) ;
	/**
	 * 删除一个值
	 * @param key
	 * @param expiry
	 * @return
	 */
	boolean delete(String key, long expiry); 
	boolean delete(String key,Integer hashCode,long expiry);
	
	/**
	 * 获取一个值 
	 * @param key
	 * @return
	 */
	Object get(String key); 
	/**
	 * 获取一个值
	 * @param key
	 * @param hashCode 集群时用于负载权重
	 * @return
	 */
	Object get(String key,Integer hashCode); 
	/**
	 * 获取一个值
	 * @param key
	 * @param hashCode 集群时用于负载权重
	 * @param asString
	 * @return
	 */
	Object get(String key,Integer hashCode, boolean asString); 
	
	/**
	 * 替换一个值,如果不存在则失败
	 * @param key
	 * @param value
	 * @return
	 */
	boolean replace(String key,Object value) ;
	/**
	 * 替换一个值 ,如果不存在则失败
	 * @param key
	 * @param value
	 * @param expiry 过期时间(毫秒)
	 * @return
	 */
	boolean replace(String key,Object value, long expiry) ;
	/**
	 * 替换一个值 ,如果不存在则失败
	 * @param key
	 * @param value
	 * @param expiry
	 * @param hashCode 集群时用于负载权重
	 * @return
	 */
	boolean replace(String key,Object value,long expiry,Integer hashCode) ;
	/**
	 * 替换一个值 ,如果不存在则失败
	 * @param key
	 * @param value
	 * @param hashCode 集群时用于负载权重
	 * @return
	 */
	boolean replace(String key, Object value, Integer hashCode) ;
	
	/**
	 * 设置一个值到缓存，如果存在则替换，否则添加
	 * @param key
	 * @param value
	 * @return
	 */
	boolean set(String key, Object value);
	/**
	 * 设置一个值到缓存，如果存在则替换，否则添加
	 * @param key
	 * @param value 
	 * @param expiry 过期时间(毫秒)
	 * @return
	 */
	boolean set(String key, Object value, long expiry); 
	/**
	 * 设置一个值到缓存，如果存在则替换，否则添加
	 * @param key
	 * @param value
	 * @param expiry 过期时间(毫秒)
	 * @param hashCode 集群时用于负载权重
	 * @return
	 */
	boolean set(String key, Object value, long expiry, Integer hashCode); 
	/**
	 * 设置一个值到缓存，如果存在则替换，否则添加
	 * @param key
	 * @param value
	 * @param hashCode 集群时用于负载权重
	 * @return
	 */
	boolean set(String key, Object value, Integer hashCode); 

	/**
	 * 判断指定key是否存在
	 * @param key
	 * @return
	 */
	boolean keyExists(String key);
	
}
