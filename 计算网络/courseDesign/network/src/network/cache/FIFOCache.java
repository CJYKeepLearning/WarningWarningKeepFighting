package network.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 固定大小的先进先出缓存
 */
public class FIFOCache<K, V> extends LinkedHashMap<K, V> {
    private static int MAX_CACHE_SIZE;

    public FIFOCache(int maxCacheSize){
        super(maxCacheSize, 0.75f, false);
        MAX_CACHE_SIZE = maxCacheSize;
    }

    /**
     * 这里采用满则覆盖之前的按顺序最先插入的
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return MAX_CACHE_SIZE < size();
    }
}
