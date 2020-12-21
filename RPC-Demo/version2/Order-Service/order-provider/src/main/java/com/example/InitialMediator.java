package com.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Evan
 */
@Component
public class InitialMediator implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(GPRemoteService.class)){
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                String  key = bean.getClass().getInterfaces()[0].getName() + "." + method.getName();
                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(method);

                Mediator.map.put(key,beanMethod);
            }
        }
        return bean;
    }
}
