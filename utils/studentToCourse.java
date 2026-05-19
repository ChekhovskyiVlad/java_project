package individual1.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import individual1.Teacher;
import java.io.IOException;

/*
эта утилита нужна чтобы когда тичер прибавляет юзера в к курсу, у него автоматически добавлялся курс 

можно сделать так что при запуске программы в общем эта утилита проверяет поточное состояние дб курсов

и когда вызывается функция enrollStudentToCourse тогда дб проверяется еще раз, и добавленный айди студента проверяет и причисляет ему курс
и потом подтягивается также таски

нужно добавить функцию студенту глянуть задание, и тогда проверяется его курсы к которым он записан
с теми которые есть(то есть проверяется две дб)и уже из дб 
courses.txt он подтягивает ему таски  

тогда по идее надо создать еще одну поддб с тасками для каждого курса

 */
public class studentToCourse {

    void studentToCourse() {
        try (FileOutputStream fos = new FileOutputStream("courses.txt", true)) {

        } catch (IOException ex) {
            ex.getMessage();
        }
    }
}
