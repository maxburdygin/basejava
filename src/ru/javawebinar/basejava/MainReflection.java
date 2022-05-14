package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.AbstractArrayStorage;
import ru.javawebinar.basejava.storage.Storage;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println("Resume toString is " + r);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        System.out.println(r);

        // TODO : invoke r.toString via reflection
        Class<?> c = r.getClass();
        Class<?> d = AbstractArrayStorage.class;
        Method toString = c.getDeclaredMethod("toString");
        System.out.println(toString.invoke(r));
        Object object = null;
        try {
            object = c.getConstructor(String.class).newInstance("123uuid");
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(object);
    }
}
