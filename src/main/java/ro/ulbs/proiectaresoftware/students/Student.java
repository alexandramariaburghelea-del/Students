package ro.ulbs.proiectaresoftware.students;

import java.util.Objects;

public class Student {
    private int numarMatricol;
    private  String prenume;
    private String nume;
    private String formatieDeStudiu;

    public Student(int numarMatricol, String prenume, String nume, String formatieDeStudiu) {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieDeStudiu = formatieDeStudiu;
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

    @Override
    public String toString() {
        return  String.format("%-10d %-12s %-12s %-10s", numarMatricol,prenume,nume,formatieDeStudiu);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return numarMatricol == student.numarMatricol && Objects.equals(prenume, student.prenume) && Objects.equals(nume, student.nume) && Objects.equals(formatieDeStudiu, student.formatieDeStudiu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numarMatricol, prenume, nume, formatieDeStudiu);
    }
}
