public class TestCustomLoader {
    public static void main(String[] args) throws Exception {
        CustomClassLoader loader = new CustomClassLoader("/path/to/classes");
        Class<?> clazz = loader.loadClass("com.example.HelloWorld");

        Object instance = clazz.getDeclaredConstructor().newInstance();
        clazz.getMethod("sayHello").invoke(instance);
    }
}
