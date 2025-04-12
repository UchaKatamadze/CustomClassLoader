import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;

/**
 * CustomClassLoader is a custom implementation of the Java ClassLoader.
 * This class allows loading of class files from a specified directory.
 * The class loader searches for .class files in the given directory and
 * loads them into the JVM.
 */
public class CustomClassLoader extends java.lang.ClassLoader {
    private final String classDir;

    public CustomClassLoader(String classDir){
        this.classDir = classDir;
    }


    @Override
    protected Class<?> findClass (String name) throws ClassNotFoundException {
        String fileName = name.replace('.', File.separatorChar) + ".class";
        File classFile = new File(classDir, fileName);

        if (!classFile.exists()){
            throw new ClassNotFoundException("Class not found:" + name);
        }

        try{
            byte[] classBytes = loadClassBytes(classFile);
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e){
            throw new ClassNotFoundException("Error loading class:", e);
        }
    }

    private byte[] loadClassBytes(File classFile) throws IOException {
        try (FileInputStream fis = new FileInputStream(classFile)) {
            byte[] buffer = new byte[(int) classFile.length()];
            fis.read(buffer);
            return buffer;
        }
    }
}
