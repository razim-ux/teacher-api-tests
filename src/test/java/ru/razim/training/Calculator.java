package ru.razim.training;

public class Calculator {
    public int sum(int a, int b){
        return a + b;
    }

    public String getFullName(String firstName, String lastName){
        return firstName + " " + lastName;
    }

    public void test(){
        Person person = new Person("Иван", "Иванов");

        String fullName = getFullName("Иван", "Иванов");

        String firstName = person.getFirstName();
        String lastName = person.getLastName();

        System.out.println(lastName);
        System.out.println(firstName);
    }
}
