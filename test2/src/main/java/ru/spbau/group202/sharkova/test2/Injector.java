package ru.spbau.group202.sharkova.test2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Injector {

    private static HashMap<Class<?>, Boolean> used = new HashMap<>(); // this private field is used for dfs


    /**
     * This method creates a new object of rootClassName class
     * using the provided implementations ('classes' list).
     * In case there are ambiguous implementations,
     * there is no implementation for some dependencies
     * or there are some cyclic dependencies,
     * an exception is thrown.
     */
    public static Object initialize(String rootClassName, ArrayList<Class<?>> classes)
            throws ClassNotFoundException,
                   AmbiguousImplementationException,
                   ImplementationNotFoundException,
                   InjectionCycleException,
                   InvocationTargetException,
                   InstantiationException,
                   IllegalAccessException {

        Class c = Class.forName(rootClassName);
        HashMap<Class, ArrayList<Class>> dependenciesGraph = new HashMap<>();
        dependenciesGraph.put(c, new ArrayList<>());
        // TODO constructors or declaredConstructors?
        // there's only one constructor though, according to the statement
        Constructor constructor = c.getConstructors()[0];

        Class<?>[] argumentTypes = constructor.getParameterTypes(); // dependencies

        for (Class cl : argumentTypes) {
                /* storing found implementations here;
                 * if there's more than one, then we'll understand this and throw an exception
                 */
            ArrayList<Class> implementations = getDependencies(classes, cl);
            if (!dependenciesGraph.containsKey(cl)) {
                dependenciesGraph.put(cl, implementations);
            } else {
                dependenciesGraph.get(cl).addAll(implementations);
            }

            dependenciesGraph.get(c).add(cl);

        }

        if (dfs(c, dependenciesGraph)) {
            throw new InjectionCycleException();
        }

        ArrayList<Object> arguments = new ArrayList<>();
        for (Class arg : argumentTypes) {
            // TODO wouldn't this do unnecessary dfs traversals since we've already detected all cyclic dependencies on the first traversal for this root class
            // TODO delete debug variable
            Object o = arg.cast(Injector.initialize(arg.getCanonicalName(), classes));
            arguments.add(o);
        }

        constructor.setAccessible(true);
        // TODO fix the fact that those are not recongizable (apparently) as objects of the necessary type and thus an exception is thrown
        return arguments.isEmpty() ? constructor.newInstance() : constructor.newInstance(arguments.toArray());

    }

    // TODO do I need this or is it enough to have just one superclass?
    /*private static ArrayList<Class> getAllSuperclasses(Class c) {
        if (c.getSuperclass() == null)
            return new ArrayList<>();

        ArrayList<Class> superclasses = new ArrayList<>();
        Class superclass = c.getSuperclass();
        superclasses.add(superclass);
        superclasses.addAll(getAllSuperclasses(superclass));

        return superclasses;
    }*/

    /**
     * This private method is used for getting all dependencies of a class.
     * Dependencies are listed in constructor arguments,
     * the dependencies of dependencies are not considered here.
     */
    private static ArrayList<Class> getDependencies(
            ArrayList<Class<?>> classes, Class<?> cl)
            throws AmbiguousImplementationException, ImplementationNotFoundException {

        HashSet<Class> implementations = new HashSet<>();
        for (Class cls : classes) {
            if (Arrays.asList(cls.getInterfaces()).contains(cl)  // this means a dependency
                    //|| getAllSuperclasses(cls).contains(cl)) { // in case we actually need all superclasses... even though I think all dependencies will be detectd by dfs
                    || cls.getSuperclass().equals(cl)
                    || cls.equals(cl)) {
                if (implementations.contains(cl)) {
                    throw new AmbiguousImplementationException();
                }

                implementations.add(cls);
            }

        }

        if (implementations.isEmpty()) {
            throw new ImplementationNotFoundException();
        }

        return (ArrayList<Class>) implementations.stream().filter(cls -> (!cls.equals(cl))).collect(Collectors.toList());
    }

    /**
     * Depths-first search is used for detecting cyclic dependecies.
     */
    private static boolean dfs(Class cl,
                               HashMap<Class, ArrayList<Class>> dependenciesGraph) {
        used.put(cl, true);

        for (Class<?> dep : dependenciesGraph.get(cl)) {
            if ((used.containsKey(dep) && used.get(dep)) || dfs(dep, dependenciesGraph)) {
                return true;
            }
        }

        return false;

    }
}
