package com.Rengar.player.pluginParser;

import com.Rengar.Plugins.IPlayerPlugin;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class PluginManager {
    private URLClassLoader urlClassLoader;

    public PluginManager(List<Plugin> plugins) throws MalformedURLException {
        init(plugins);
    }

    private void init(List<Plugin> plugins) throws MalformedURLException {
        int size = plugins.size();
        URL[] urls = new URL[size];

        for(int i = 0; i < size; i++) {
            Plugin plugin = plugins.get(i);
            String filePath = plugin.getJar();

            urls[i] = new URL("file:" + filePath);
        }

        urlClassLoader = new URLClassLoader(urls);
    }

    public IPlayerPlugin getInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> clazz = urlClassLoader.loadClass(className);
        Object instance = clazz.newInstance();

        return (IPlayerPlugin)instance;
    }
}