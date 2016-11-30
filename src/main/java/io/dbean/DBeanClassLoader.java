package io.dbean;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public final class DBeanClassLoader {

    private String packageName;

    private DBeanClassLoader(String packageName) {
        this.packageName = packageName;
    }

    public static DBeanClassLoader scan(String packageName) {
        return new DBeanClassLoader(packageName);
    }

    public List<Class<?>> loadAll() throws IOException, ClassNotFoundException {
        return loadAllClasses(this.packageName);
    }

    public List<String> findAll() throws IOException {
        return findAllClassNames(this.packageName);
    }

    public static List<Class<?>> loadAllClasses(String packageName)
            throws ClassNotFoundException, IOException {
        List<Class<?>> classes = new ArrayList<>();
        for(String className : findAllClassNames(packageName)) {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            assert classLoader != null;
            classes.add(classLoader.loadClass(className));
        }
        return classes;
    }

    public static List<String> findAllClassNames(String packageName)
            throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        List<String> classNames = new ArrayList<>();
        for (File directory : dirs) {
            classNames.addAll(findClassNames(directory, packageName));
        }
        return classNames;
    }

    private static List<String> findClassNames(File directory, String packageName) {
        List<String> classNames = new ArrayList<>();
        if (!directory.exists()) {
            return classNames;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classNames.addAll(findClassNames(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classNames.add(packageName + '.' + file.getName().replace(".class", ""));
            }
        }
        return classNames;
    }

}
