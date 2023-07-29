package org.ycframework.context;

import org.springframework.beans.factory.config.BeanDefinition;

/**
 * 对一个Bean特征的包装类
 * 特征：1.是单例吗scope（singleton/prototype） 默认单例
 *      2.是懒加载的吗lazy（true/false）
 *      3.是不是主类primary（主实例|优先实例）  UserDao接口：@primary UserDaoMybatisImpl类 UserDaoHibernateImpl类
 *                                  业务层@Autowired + @Qualifier
 *                                      @Autowired + Primary
 */

public class YcBeanDefinition {
    private boolean isLazy;
    private String scope = "Singleton";
    private boolean isPrimary;
    //...

    private String classInfo; //类的实例信息

    public boolean isLazy() {
        return isLazy;
    }

    public void setLazy(boolean lazy) {
        isLazy = lazy;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public String getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo;
    }
}
