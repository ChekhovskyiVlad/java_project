package individual1;

import java.util.Scanner;

/// все дальнейшие значение будут просто дописываться уже к существующему аккаунта, к примеру какие курсы, какие оценки
// может тогда имеет смысл 

/*
когда юзер заходит в свой аккаунт, просто подтягиваются его оценки сразу с бинарника, вот и все

то есть после того как юзер залогинился ему на панель отображается что за оценки у него на какие курсы он записан

лучше всего полностью строку выносить, и так показывать на панеле 

то есть это нужно прописать в мейне, что когда логин успешный, подтягиваютс все эти данные и записываются в функцию,

сначала напишу эту логику на мейне потом чет перенесу


нет эту логику нужно прописывать в самом классе логина и регистрации и от туда уже должно все подтягиваться 


можно сделать еще один класс который будет вызывать этапы логика, регистрации студента и тд
*/


public class Teacher extends Account {

    private String Courses[];

    void createCourse() {
        System.out.print("Enter the name of course: ");
        Scanner in = new Scanner(System.in);

        String nameCourse = in.nextLine();

        System.out.print("Add ID of your students: ");

        String idStudent = in.nextLine();
    }

    void enrollStudentToCourse() {

    }

    void assignGradeToStudent() {

    }

    void printInfoTeacher() {

    }
}
