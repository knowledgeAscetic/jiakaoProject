package com.wang.jk.common.Shiro;

import com.wang.jk.filter.ErrorFilter;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public Realm getRealm()
    {
        return new TokenRealm(new TokenMatcher());
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(Realm realm)
    {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        factoryBean.setSecurityManager(manager);

        //往shiro内设置自定义的filter，后续可以通过key值来设置拦截路径调用的filter
        HashMap<String, Filter> filterHashMap = new HashMap<>();
        filterHashMap.put("token",new TokenFilter());
        factoryBean.setFilters(filterHashMap);

        //设置路径对应的filter anon不继续任何处理，直接放行
        //token 自定义的filter
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/sysUsers/login","anon");
        map.put("/sysUsers/captcha","anon");
        map.put(ErrorFilter.ERROR_URL,"anon");
        map.put("/**","token");
        factoryBean.setFilterChainDefinitionMap(map);

        return factoryBean;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator proxyCreator()
    {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setUsePrefix(true);
        return proxyCreator;
    }
}
