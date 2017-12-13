package ru.spbau.group202.sharkova.test2;


import org.junit.Test;
import testClasses.ClassWithOneClassDependency;
import testClasses.ClassWithOneInterfaceDependency;
import testClasses.ClassWithoutDependencies;
import testClasses.InterfaceImpl;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertTrue;


public class InjectorTest {

    @Test
    public void injectorShouldInitializeClassWithoutDependencies()
            throws Exception {
        Object object = Injector.initialize("testClasses.ClassWithoutDependencies", new ArrayList<>());
        assertTrue(object instanceof ClassWithoutDependencies);
    }

    @Test
    public void injectorShouldInitializeClassWithOneClassDependency()
            throws Exception {
        ArrayList<String> impl = new ArrayList<>(Collections.singletonList("testClasses.ClassWithoutDependencies"));
        ArrayList<Class<?>> classes = new ArrayList<>();
        for (String name : impl) {
            classes.add(Class.forName(name));
        }

        Object object = Injector.initialize(
                "testClasses.ClassWithOneClassDependency", classes);
        assertTrue(object instanceof ClassWithOneClassDependency);
        ClassWithOneClassDependency instance = (ClassWithOneClassDependency) object;
        assertTrue(instance.dependency != null);
    }

    @Test
    public void injectorShouldInitializeClassWithOneInterfaceDependency()
            throws Exception {

        ArrayList<String> impl = new ArrayList<>(Collections.singletonList("testClasses.ClassWithoutDependencies"));
        ArrayList<Class<?>> classes = new ArrayList<>();
        for (String name : impl) {
            classes.add(Class.forName(name));
        }

        Object object = Injector.initialize(
                "testClasses.ClassWithOneInterfaceDependency",
                classes);
        assertTrue(object instanceof ClassWithOneInterfaceDependency);
        ClassWithOneInterfaceDependency instance = (ClassWithOneInterfaceDependency) object;
        assertTrue(instance.dependency instanceof InterfaceImpl);
    }
}