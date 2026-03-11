package singleton_understanding;

public class A {

    private static A a;

    public static A getInstance(){
        if (a == null){
            a = new A();
            return a;
        } else {
            return a;
        }
    }
}
