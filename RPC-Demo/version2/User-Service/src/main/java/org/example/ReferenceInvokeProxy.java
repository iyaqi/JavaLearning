package org.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * @author Evan
 */
@Component
public class ReferenceInvokeProxy implements BeanPostProcessor {

    @Autowired
    private RemoteInvocationHandler remoteInvocationHandler;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(YQReference.class)){
                field.setAccessible(true);

                Object proxy = Proxy.newProxyInstance(field.getType().getClassLoader(),
                        new Class[]{field.getType()},
                        remoteInvocationHandler);
                try {
                    field.set(bean,proxy);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }

        return bean;
    }
}
