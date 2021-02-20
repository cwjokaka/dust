package com.ls.dust.common;

import java.lang.reflect.InvocationTargetException;

/**
 * 反射工具
 */
public class ReflectionUtils {

    /**
     * 根据类生成实例对象
     * @param clazz 类
     * @return 实例对象
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 在当前线程调用栈中获取以callerSupertype为父类对应的callingMethodName方法的目标类
     * @param callerSupertype 父类
     * @param callingMethodName 调用的方法
     * @return 目标类
     */
    public static <T> Class<? extends T> getCallingClass(Class<T> callerSupertype, String callingMethodName) {
        try {
            StackTraceElement[] cause = Thread.currentThread().getStackTrace();
            boolean foundThisMethod = false;
            String callingClassName = null;
            for (StackTraceElement se : cause) {
                String className = se.getClassName();
                String methodName = se.getMethodName();
                if (foundThisMethod) {
                    callingClassName = className;
                    break;
                } else if (callerSupertype.getName().equals(className) && callingMethodName.equals(methodName)) {
                    foundThisMethod = true;
                }
            }

            if (callingClassName == null) {
                throw new RuntimeException("Error: unable to determine calling class name");
            }

            Class<?> theClass = Class.forName(callingClassName, false, Thread.currentThread().getContextClassLoader());
            if (!callerSupertype.isAssignableFrom(theClass)) {
                throw new RuntimeException("Error: " + theClass + " is not a subclass of " + callerSupertype);
            }

            return (Class<? extends T>) theClass;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
