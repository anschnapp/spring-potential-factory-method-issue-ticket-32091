package com.example.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class StaticFactoryMethodUtil {
    // this is the method which should be called via factory bean declaration
    public static <T> T create(Class<T> classToCreate) {
        return createViaReflection(classToCreate);
    }

    // this method would be also a candidate for determining the return type in
    // org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory
    // method getTypeForFactoryMethod
    // it's not an issue because it finds the right concrete type InnerService
    public static <T> T create(String name, Class<T> classToCreate) {
        return createViaReflection(classToCreate);
    }

    // this method would be also a candidate for determining the return type in
    // org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory
    // method getTypeForFactoryMethod
    // it's a problem because it would result in type java.lang.Object
    public static <T> T create(String name, T self) {
        return self;
    }

    // this method would be also a candidate for determining the return type in
    // org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory
    // method getTypeForFactoryMethod
    // it's a problem because it would result in type java.lang.Object
    public static String create(String name) {
        return name;
    }

    // this method would be also a candidate for determining the return type in
    // org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory
    // method getTypeForFactoryMethod
    // it's a problem because it would result in type java.lang.Object
    public static <T> T create(String name, T... reified) {
        return createViaReflection(getClassOf(reified));
    }

    // just that the method would work in general and we don't get side effects based on wrong implementations
    private static <T> Class<T> getClassOf(T[] array) {
        return (Class<T>) array.getClass().getComponentType();
    }

    // just that the method would work in general and we don't get side effects based on wrong implementations
   private static <T> T createViaReflection(Class<T> classToCreate) {
       try {
            Constructor<?> constructor = classToCreate.getDeclaredConstructor();
            constructor.setAccessible(true);
           return (T) constructor.newInstance();
       } catch (InstantiationException e) {
           throw new RuntimeException(e);
       } catch (IllegalAccessException e) {
           throw new RuntimeException(e);
       } catch (InvocationTargetException e) {
           throw new RuntimeException(e);
       } catch (NoSuchMethodException e) {
           throw new RuntimeException(e);
       }
   }
}
