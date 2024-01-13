package io.github.colindj1120.materialdesignui.utils;

public class ClassUtils {
    /**
     * To force initialization of a class
     *
     * @param classToInit
     *         The class to initialize
     */
    public static void forceInit(final Class<?> classToInit) {
        try {
            Class.forName(classToInit.getName(), true,
                          classToInit.getClassLoader());
        }
        catch (final ClassNotFoundException e) {
            throw new AssertionError(e);  // Can't happen
        }
    }
}
