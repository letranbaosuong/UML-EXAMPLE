package Bai_2;

import java.util.ArrayList;

public class test_bai_2 {
    public static void main(String[] args) {
        Person person1 = new Person("John Brown", "123 Nguyen Trai");
//        System.out.println("person1 is Person[name='" + person1.getName() +
//                "', address='" + person1.getAddress() + "']");
        System.out.println("person1 is " + person1.toString());

        Student student1 = new Student("Marry White", "175 Dien Bien Phu", "Software", 1, 2000.0);
//        System.out.println("student1 is Student{Person[ name = " + student1.getName() + ", address='" +
//                student1.getAddress() + "']," +
//                "program='" + student1.getProgram() + "', year=" + student1.getYear() + ", fee=" + student1.getFee() + "}");
        System.out.println("student1 is {" + student1.toString() + "}");

        Staff staff1 = new Staff("Bill Smith", "300 Pham Van Hai", "Marie Curie", 1500.0);
//        System.out.println("staff1 is Staff{Person[name='" + staff1.getName() + "', address='" + staff1.getName() + "'],\n" +
//                "school='" + staff1.getSchool() + "', pay=" + staff1.getPay() + "}");
        System.out.println("staff1 is {" + staff1.toString() + "}");

//        System.out.println("persons are:\n" +
//                "Person[name='John Brown', address='123 Nguyen Trai']\n" +
//                "Student{Person[name='Mary White', address='175 Dien Bien Phu'],\n" +
//                "program='Software', year=1, fee=2000.0}\n" +
//                "Staff{Person[name='Bill Smith', address='300 Pham Van Hai'], school='Marrie\n" +
//                "Currie', pay=1500.0}");

        ArrayList<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(student1);
        persons.add(staff1);
        System.out.println("persons are:" + persons.toString());
    }
}
