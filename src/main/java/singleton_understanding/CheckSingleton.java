package singleton_understanding;

public class CheckSingleton {

    public static void main(String[] args) {

//        A obj1 = new A();
//        System.out.println(obj1);
//
//        A obj2 = new A();
//        System.out.println(obj2);

        A obj1 = A.getInstance();
        A obj2 = A.getInstance();
        System.out.println(obj1);
        System.out.println(obj2);
    }
}
