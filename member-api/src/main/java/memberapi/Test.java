package memberapi;

import java.util.ArrayList;

/**
 * @author agj017@gmail.com
 * @since 2021/10/20
 */

class Hi{

    public void aa(){
        System.out.println("aa");
    }
}

class Hello<T>{
    T a;
    ArrayList <T> arrayList = new ArrayList<T>();

    ArrayList <String> arrayList2 = new ArrayList<String>();

//    public Hello(T a) {
//        this.a = a;
//    }

    static void method(ArrayList<? extends Object> a){

    }



    T world(T b){
        T c = b;


        return c;
    }

}

public class Test {

    public static void main(String[] args) {

        new Hello<String>();

        new Hello<Long>();

    }
}
