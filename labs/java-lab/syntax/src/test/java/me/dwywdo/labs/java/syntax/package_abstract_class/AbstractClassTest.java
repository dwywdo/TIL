package me.dwywdo.labs.java.syntax.package_abstract_class;

import org.junit.jupiter.api.Test;

import me.dwywdo.labs.java.syntax.package_abstract_class.ex1.Dog;
import me.dwywdo.labs.java.syntax.package_abstract_class.ex2.Figure;
import me.dwywdo.labs.java.syntax.package_abstract_class.ex3.Battlecruiser;
import me.dwywdo.labs.java.syntax.package_abstract_class.ex3.Dropship;
import me.dwywdo.labs.java.syntax.package_abstract_class.ex3.Marine;
import me.dwywdo.labs.java.syntax.package_abstract_class.ex3.Tank;
import me.dwywdo.labs.java.syntax.package_abstract_class.ex3.Unit;

public class AbstractClassTest {
    @Test
    void instantiate() {
        final Dog myDog = new Dog();
        // 상속되는 Pet 추상 클래스의 추상 메서드를 Dog에서 구현하고, 구현한 메서드를 호출
        myDog.eat();
        myDog.walk();

        // 상속되는 Pet 추상 클래스가 직접 구현한 메서드를 Dog에서 호출
        myDog.run();
    }

    @Test
    void constructorRelationship() {
        final Figure figure = new Figure("ShapeType", "FigureName");
        System.out.println("figure.type = " + figure.type);
        System.out.println("figure.name = " + figure.name);
        System.out.println("figure.subtype = " + figure.subtype);
    }

    @Test
    void forceToImplement() {
        /*
        `abstract`라는 추상 키워드를 사용하여 클래스와 메서드를 명시하지 않으면
        -> move() 메서드의 바디를 부모 클래스도 가지게 된다.
        -> 하위 클래스가 추가될 때 move() 메서드 구현에 대한 강제성이 사라진다
        -> Battlecruiser 임에도 상위 클래스의 move() 메서드가 호출되어버린다.
        -> Runtime 상황에서 문제가 발생할 수 있다.
         */
        final Unit[] group = new Unit[4];
        group[0] = new Marine();
        group[1] = new Tank();
        group[2] = new Dropship();
        group[3] = new Battlecruiser();

        for (Unit unit: group) {
            unit.move(100, 200);
        }
    }
}
