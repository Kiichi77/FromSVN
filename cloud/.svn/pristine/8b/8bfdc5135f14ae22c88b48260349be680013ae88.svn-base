package cn.com.kxcomm.memcached;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;

/**缓存的一个实现类(memcached client)
 * @author lydawen 2009-11-27
 * 
 * 
 */
public class CacheClientImpl implements ICacheClient {

	private MemCachedClient cacheProvider;
	
	@Override
	public boolean add(String key, Object value) {
		return this.cacheProvider.add(key, value);
	}

	@Override
	public boolean add(String key, Object value, long expiry) {
		return this.cacheProvider.add(key, value, new Date(expiry));
	}

	@Override
	public boolean add(String key, Object value, long expiry, Integer hashCode) {
		return this.cacheProvider.add(key, value, new Date(expiry), hashCode);
	}

	@Override
	public boolean add(String key, Object value, Integer hashCode) {
		return this.cacheProvider.add(key, value, hashCode);
	}

	@Override
	public boolean delete(String key) {
		return this.cacheProvider.delete(key);
	}

	@Override
	public boolean delete(String key, long expiry) {
		return this.cacheProvider.delete(key, new Date(expiry));
	}

	@Override
	public boolean delete(String key, Integer hashCode, long expiry) {
		return this.cacheProvider.delete(key,hashCode,new Date(expiry));
	}

	@Override
	public Object get(String key) {
		return this.cacheProvider.get(key);
	}

	@Override
	public Object get(String key, Integer hashCode) {
		return this.cacheProvider.get(key, hashCode);
	}

	@Override
	public Object get(String key, Integer hashCode, boolean asString) {
		return this.cacheProvider.get(key, hashCode, asString);
	}

	@Override
	public boolean keyExists(String key) {
		return this.cacheProvider.keyExists(key);
	}

	@Override
	public boolean replace(String key, Object value) {
		return this.cacheProvider.replace(key, value);
	}

	@Override
	public boolean replace(String key, Object value, long expiry) {
		return this.cacheProvider.replace(key, value, new Date(expiry));
	}

	@Override
	public boolean replace(String key, Object value, long expiry,
			Integer hashCode) {
		return this.cacheProvider.replace(key, value, new Date(expiry), hashCode);
	}

	@Override
	public boolean replace(String key, Object value, Integer hashCode) {
		return this.cacheProvider.replace(key, value, hashCode);
	}

	@Override
	public boolean set(String key, Object value) {
		return this.cacheProvider.set(key, value);
	}

	@Override
	public boolean set(String key, Object value, long expiry) {
		return this.cacheProvider.set(key, value, new Date(expiry));
	}

	@Override
	public boolean set(String key, Object value, long expiry, Integer hashCode) {
		return this.cacheProvider.set(key, value, new Date(expiry), hashCode);
	}

	@Override
	public boolean set(String key, Object value, Integer hashCode) {
		return this.cacheProvider.set(key, value, hashCode);
	}
	
	
	public MemCachedClient getCacheProvider() {
		return cacheProvider;
	}

	public void setCacheProvider(MemCachedClient cacheProvider) {
		this.cacheProvider = cacheProvider;
	}

}
