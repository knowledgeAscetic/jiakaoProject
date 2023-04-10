package com.wang.jk.common.cache;

import com.wang.jk.pojo.dto.SysUserDto;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

public class EhTokenListenter implements CacheEventListener<String,Object> {
    @Override
    public void onEvent(CacheEvent<? extends String, ?> cacheEvent) {
        String token = cacheEvent.getKey();
        switch (cacheEvent.getType())
        {
            case CREATED:
                SysUserDto newValue = (SysUserDto) cacheEvent.getNewValue();
                Caches.putUserToToken(newValue.getId(),token);
                break;
            case REMOVED:
            case EXPIRED:
                SysUserDto oldValue = (SysUserDto) cacheEvent.getOldValue();
                Caches.removeUserToToken(oldValue.getId());
                break;
        }
    }
}
