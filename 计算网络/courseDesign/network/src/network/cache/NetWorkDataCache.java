package network.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 数据包对应的缓存
 */
public class NetWorkDataCache implements INetWorkData {
    private static final FIFOCache<Integer, String> cache = new FIFOCache<>(CACHE_SIZE);

    public static List<String> getCacheList() {
        Set<Integer> keySet = cache.keySet();
        List<String> retList = new ArrayList<>(keySet.size());
        for(Integer key: keySet){
            retList.add(cache.get(key));
        }
        return retList;
    }

    public static void put(Integer key, String value){
        synchronized (String.class) {
            cache.put(key, value);
        }
    }

    public static String get(Integer key){
        return cache.get(key);
    }

    public static String listToString(){
        List<String> cacheList = getCacheList();
        StringBuilder builder = new StringBuilder();
        for(String netWorkData: cacheList){
            builder.append(netWorkData).append("\n");
        }
        return builder.toString();
    }

    public static int getSize() {
        return cache.size();
    }
}
