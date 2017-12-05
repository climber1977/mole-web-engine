package com.mole.webengine.utils;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 插件类加载器，在插件目录中搜索jar包，并为发现的资源(jar)构造一个类加载器,将对应的jar添加到classpath中
 * @author strawxdl
 */
public class PluginClassLoader extends URLClassLoader {
	final static Logger logger = LoggerFactory.getLogger(PluginClassLoader.class);
	
    JarURLConnection jarUrl = null;
    public PluginClassLoader() {
        super(new URL[] {}, findParentClassLoader());
    }

    /**
     * 将指定的文件url添加到类加载器的classpath中去，并缓存jar connection，方便以后卸载jar
     * @param 一个可想类加载器的classpath中添加的文件url
     * @throws IOException 
     */
    public void addURLFile(URL file) throws IOException {
    	try {
    		logger.info(file.getPath());
        	logger.info(file.getFile());
            URLConnection uc = file.openConnection();
            if (uc instanceof JarURLConnection) {
                uc.setUseCaches(true);
                ((JarURLConnection) uc).getManifest();
                jarUrl = (JarURLConnection)uc;
            }
          
            addURL(file);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    	
    }
    
    /**
     * 卸载jar包
     * @throws IOException 
     */
    public void unloadJarFiles() throws IOException {
        jarUrl.getJarFile().close();
        jarUrl=null;
      
    }

    /**
     * 定位基于当前上下文的父类加载器
     * @return 返回可用的父类加载器.
     */
    private static ClassLoader findParentClassLoader() {
        ClassLoader parent = PluginManager.class.getClassLoader();
        if (parent == null) {
            parent = PluginClassLoader.class.getClassLoader();
        }
        if (parent == null) {
            parent = ClassLoader.getSystemClassLoader();
        }
        return parent;
    }
}