package boot.spring.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import boot.spring.realm.UserRealm;

@Configuration
public class ShiroConfig {
	
	@Bean
	DefaultPasswordService pwdService(){
		return new DefaultPasswordService();
	}

    @Bean("userRealm")
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        PasswordMatcher pwdmatcher = new PasswordMatcher();
        pwdmatcher.setPasswordService(pwdService());
        userRealm.setCredentialsMatcher(pwdmatcher);
        return userRealm;
    }
    
    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager")
                               DefaultWebSecurityManager securityManager) {
        
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置 SecurityManager
        bean.setSecurityManager(securityManager);
		// 设置登录成功跳转Url
        bean.setSuccessUrl("/html/index");
        // 设置登录跳转Url
        bean.setLoginUrl("/html/login.html");
        // 设置未授权提示Url
        bean.setUnauthorizedUrl("/html/unauthorized.html");
        
        /**
         * anon：匿名用户可访问
         * authc：认证用户可访问
         * user：使用rememberMe可访问
         * perms：对应权限可访问
         * role：对应角色权限可访问
         **/
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/html/authImg","anon");
        filterMap.put("/css/**","anon");
        filterMap.put("/img/**","anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/plugins/**","anon");
        filterMap.put("/html/login.html","anon");
        
        filterMap.put("/html/index.html","user");
        filterMap.put("/html/roleadmin.html","roles[\"admin\"]");
        filterMap.put("/html/index.html","user");
        
//        filterMap.put("/logout", "logout");
        filterMap.put("/**","anon");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

    /**
     * 注入 securityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        
        DefaultWebSecurityManager securityManager = 
            new DefaultWebSecurityManager();
        // 关联realm.
        securityManager.setRealm(userRealm());
        return securityManager;
    }
}
