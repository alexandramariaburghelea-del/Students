package ro.ulbs.proiectaresoftware.students;

import java.util.ArrayList;
import java.util.List;

public class Appliction {
    public static void main(){
        Student s1 = new Student(112, "Ioan", "Popa", "TI21/1");
        Student s2 = new Student(112, "Maria", "Oprea", "TI21/1");
        Student s3 = new Student(120, "Alis", "Popa", "TI21/2");
        Student s4 = new Student(122, "Mihai", "Vecerdea", "TI22/1");
        Student s5 = new Student(122, "Eugen", "Uritescu", "TI22/2");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
        List<Student> listaStudenti=new ArrayList<>();
        listaStudenti.add(new Student(112, "Ioan", "Popa", "TI21/1"));
        listaStudenti.add(new Student(112, "Maria", "Oprea", "TI21/1"));
        listaStudenti.add(new Student(120, "Alis", "Popa", "TI21/2"));
        listaStudenti.add(new Student(122, "Mihai", "Vecerdea", "TI22/1"));
        listaStudenti.add(new Student(122, "Eugen", "Uritescu", "TI22/2"));

        for(Student s:listaStudenti)
            System.out.println(s);

        Student s6 = new Student(120, "Alis", "Popa", "TI21/2");
        boolean exista = listaStudenti.contains(s6);
        System.out.println("Studentul: "+s6+" exista:"+exista);
        Student s7=new Student(112, "Maria", "Popa", "TI21/1");
        exista=listaStudenti.contains(s7);
        System.out.println("Studentul: "+s7+" exista:"+exista);
    }
}
