package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok h1 = new HelloLombok();
        h1.setAge(100);
        System.out.println(h1.getAge());

        h1.setName("minseok");
        System.out.println(h1.getAge());

        System.out.println(h1); // ToString 의 기능
    }
}


