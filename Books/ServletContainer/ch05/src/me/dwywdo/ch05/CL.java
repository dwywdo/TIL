package me.dwywdo.ch05;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class CL {
    public static void main(String[] args) {
        CL cl = new CL();
        cl.action();
    }

    private void action() {
        init();
        load();
    }

    private void init() {
        String contextPath = "/Users/dwywdo/private/TIL/Books/ServletContainer/ch05/webctx";
        String classesPath = contextPath
                .concat(File.separator)
                .concat("WEB-INF")
                .concat(File.separator)
                .concat("classes");

        String libPath = contextPath
                .concat(File.separator)
                .concat("WEB-INF")
                .concat(File.separator)
                .concat("lib");

        File classes = new File(classesPath);
        List<URL> urlList = new ArrayList<URL>();
        if (classes.exists()) {
            try {
                urlList.add(classes.toURI().toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        File lib = new File(libPath);
        if (lib.exists()) {
            try {
                FileFilter ff = new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        boolean result = false;
                        if (pathname.getName().endsWith(".jar")) {
                            result = true;
                        }
                        return result;
                    }
                };
                File[] jarList = lib.listFiles(ff);
                for (File file : jarList) {
                    urlList.add(file.toURI().toURL());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        URL[] urls = new URL[urlList.size()];
        for (int i=0; i< urls.length; i++) {
            urls[i] = urlList.get(i);
        }
        urlCL = new URLClassLoader(urls,
                                   Thread.currentThread().getContextClassLoader());
        Thread.currentThread().setContextClassLoader(urlCL);
    }

    private void load() {
        Class calleeClass = null;
        try {
            calleeClass = urlCL.loadClass("CalleeImpl");
            Callee calleeInstance = (Callee)calleeClass.newInstance();
            System.out.println(calleeInstance.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    URLClassLoader urlCL = null;
}
