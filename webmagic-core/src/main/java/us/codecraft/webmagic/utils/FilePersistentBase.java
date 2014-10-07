package us.codecraft.webmagic.utils;

import java.io.File;

import org.junit.Test;

/**
 * Base object of file persistence.
 * 主要用于路径的处理
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
public class FilePersistentBase {

    protected String path;

    /**
     * 初始化，在windows中，分隔符为'\\'
     */
    public static String PATH_SEPERATOR = "/";

    static {
        String property = System.getProperties().getProperty("file.separator");
        if (property != null) {
            PATH_SEPERATOR = property;
        }
    }

    /**
     * 如果不是以分隔符结尾，则加一个分隔符
     * @param path
     */
    public void setPath(String path) {
        if (!path.endsWith(PATH_SEPERATOR)) {
            path += PATH_SEPERATOR;
        }
        this.path = path;
    }

    /**
     * 通过地址，返回一个file
     * @param fullName
     * @return
     */
    public File getFile(String fullName) {
        checkAndMakeParentDirecotry(fullName);
        return new File(fullName);
    }
    
    @Test
    public void getFileTest(){
    	File f = getFile("c:\\ss\\ss");
    	System.out.println(f.getName());
    }

    /**
     * 检查uri的目录是否已经存在，不存在就新建一个
     * @param fullName
     */
    public void checkAndMakeParentDirecotry(String fullName) {
        int index = fullName.lastIndexOf(PATH_SEPERATOR);
        if (index > 0) {
            String path = fullName.substring(0, index);
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }

    public String getPath() {
        return path;
    }
}
