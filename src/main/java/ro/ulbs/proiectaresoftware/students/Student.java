package ro.ulbs.proiectaresoftware.students;

import java.util.*;

public class Student {
    private final int numarMatricol;
    private final String prenume;
    private final String nume;
    private final String formatieDeStudiu;
    private final double nota;

    public Student(int numarMatricol, String prenume, String nume, String formatieDeStudiu,double nota) {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieDeStudiu = formatieDeStudiu;
        this.nota=nota;
    }
    public Student(int numarMatricol, String prenume, String nume, String formatieDeStudiu) {
        this(numarMatricol, prenume, nume, formatieDeStudiu, 0);
    }

    public int getNumarMatricol() {
        return numarMatricol;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNume() {
        return nume;
    }

    public String getFormatieDeStudiu() {
        return formatieDeStudiu;
    }

    public Student cuFormatieNoua(String formatieNoua){
        return new Student(numarMatricol, prenume, nume, formatieDeStudiu, nota);
    }

    static Student schimbaFormatia(Student st, String nouaFormatie) {
        return st.cuFormatieNoua(nouaFormatie);
    }
    static Set<Student> imparteInDouaFormatii(Set<Student> studenti,
                                              String formatia1,
                                              String formatia2) {

        List<Student> lista = new ArrayList<>(studenti);
        Set<Student> rezultat = new HashSet<>();

        int mid = (lista.size() + 1) / 2;

        for (int i = 0; i < lista.size(); i++) {
            Student s = lista.get(i);

            if (i < mid) {
                rezultat.add(schimbaFormatia(s, formatia1));
            } else {
                rezultat.add(schimbaFormatia(s, formatia2));
            }
        }

        return rezultat;
    }

    @Override
    public String toString() {
        return  String.format( numarMatricol+", "+prenume+", "+nume+", "+formatieDeStudiu+", "+nota);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return numarMatricol ==  student.numarMatricol &&
                Objects.equals(prenume, student.prenume) &&
                Objects.equals(nume, student.nume) &&
                Objects.equals(formatieDeStudiu, student.formatieDeStudiu);

    }

    @Override
    public int hashCode() {
        return Objects.hash(numarMatricol, prenume, nume, formatieDeStudiu);
    }

    /*public void setNota(double nota) {
        this.nota = nota;
    }*/
    public double getNota() {return nota;}
}

