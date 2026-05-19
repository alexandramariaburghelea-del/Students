package ro.ulbs.proiectaresoftware.students;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;

import static ro.ulbs.proiectaresoftware.students.Student.imparteInDouaFormatii;

public class Appliction {
    public static void main(String[] args) {
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
        Set<Student> setStudenti = new HashSet<>();
        setStudenti.add(new Student(112, "Ioan", "Popa", "TI21/1"));
        setStudenti.add(new Student(112, "Maria", "Oprea", "TI21/1"));
        setStudenti.add(new Student(120, "Alis", "Popa", "TI21/2"));
        setStudenti.add(new Student(122, "Mihai", "Vecerdea", "TI22/1"));
        setStudenti.add(new Student(122, "Eugen", "Uritescu", "TI22/2"));

        for (Student s : setStudenti)
            System.out.println(s);

        Student s6 = new Student(120, "Alis", "Popa", "TI21/2");
        boolean exista = setStudenti.contains(s6);
        System.out.println("Studentul: " + s6 + " exista:" + exista);
        Student s7 = new Student(112, "Maria", "Popa", "TI21/1");
        exista = setStudenti.contains(s7);
        System.out.println("Studentul: " + s7 + " exista:" + exista);

        Path path = Paths.get("studenti_in.txt");

        try {
            List<Student> listaStudenti = new ArrayList<>();
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] campuri = line.split(",");

                int nrMatricol = Integer.parseInt(campuri[0]);
                String prenume = campuri[1];
                String nume = campuri[2];
                String formatie = campuri[3];

                Student s = new Student(nrMatricol, prenume, nume, formatie);
                listaStudenti.add(s);
            }
            System.out.println("Studenti cititi:");
            for (Student s : listaStudenti) {
                System.out.println(s);
            }
            listaStudenti.sort(
                    Comparator.comparing(Student::getFormatieDeStudiu)
                            .thenComparing(Student::getNume)
            );
            List<String> linesOut = new ArrayList<>();

            for (Student s : listaStudenti) {
                String linie = s.getNumarMatricol() + "," +
                        s.getPrenume() + "," +
                        s.getNume() + "," +
                        s.getFormatieDeStudiu();

                linesOut.add(linie);
            }
            Path outPath = Paths.get("studenti_out_sorted.txt");
            Files.write(outPath, linesOut);

            System.out.println("\nSalvat in studenti_out_sorted.txt");


            //lab4
            Map<Integer, Student> mapStudenti = new HashMap<>();

            for (Student s : listaStudenti) {
                mapStudenti.put(s.getNumarMatricol(), s);
            }
            Path pathNote = Paths.get("note_anon.txt");
            List<String> noteLines = Files.readAllLines(pathNote);

            for (String line : noteLines) {
                String[] parts = line.split(",");

                int nr = Integer.parseInt(parts[0]);
                float nota = Float.parseFloat(parts[1]);

                Student s = mapStudenti.get(nr); // 🔥 O(1)
                if (s != null) {
                    //s.setNota(nota);
                }
            }
            System.out.println("\nStudenti cu note:");

            for (Student s : mapStudenti.values()) {
                System.out.println(s);
            }

            double notaM = gasesteNota("Bianca", "Popescu", mapStudenti);
            double notaN = gasesteNota("Ioan", "Popa", mapStudenti);

            System.out.println("\nNota Bianca Popescu: " + notaM);
            System.out.println("\nNota Ioan Popa: " + notaN);

        } catch (IOException e) {
            e.printStackTrace();
        }


        //lab5
        List<StudentBursier> bursieri = new ArrayList<>();

        bursieri.add(new StudentBursier(1025, "Andrei", "Popa", "ISM141/2", 8.70, 725.50));
        bursieri.add(new StudentBursier(1024, "Ioan", "Mihalcea", "ISM141/1", 9.80, 801.10));
        bursieri.add(new StudentBursier(1026, "Anamaria", "Prodan", "TI131/1", 8.90, 745.50));
        bursieri.add(new StudentBursier(1029, "Bianca", "Popescu", "TI131/1", 9.10, 780.80));

        for (StudentBursier b : bursieri) {
            System.out.println(b);
        }
        salveazaInFisier("bursieri_out.txt", bursieri);

        //lab7
        setStudenti = imparteInDouaFormatii(setStudenti, "TI 211_1", "TI 211_2");
        System.out.println("\nDupa impartire:");
        for (Student s : setStudenti) {
            System.out.println(s);
        }

        //lab9
        List<Student> studentiCuNote = Arrays.asList(
                new Student(1025,"Andrei","Popa","ISM141/2", 8.70),
                new Student(1024,"Ioan","Mihalcea","ISM141/1", 10),
                new Student(1026,"Anamaria","Prodan","TI131/1", 8.90),
                new Student(1029,"Bianca","Popescu","TI131/1,", 10),
                new Student(1029,"Maria","Pana","TI131/2,", 4.10),
                new Student(1029,"Gabriela","Mohanu","TI131/2,", 7.33),
                new Student(1029,"Marius","Nasta","TI131/2,", 3.20),
                new Student(1029,"Marius","Nasta","TI131/1,", 5.12),
                new Student(1029,"Andrei","Dobrescu","TI131/2,", 2.22)
        );
        //a)
        System.out.println("Studentii cu 10: ");
        studentiCuNote.stream().filter(x->x.getNota()==10).forEach(System.out::println);

        //b)
        System.out.println("Studentii cu nota<5: ");
        studentiCuNote.stream().filter(x->x.getNota()<5).forEach(System.out::println);

        //c)
        List<Student> studentiCorectati = studentiCuNote.stream().map(x->new Student(
                x.getNumarMatricol(),
                x.getPrenume(),
                x.getNume(),
                x.getFormatieDeStudiu(),
                Math.max(x.getNota(),4))).toList();
        System.out.println("Studentii corectati: ");
        studentiCorectati.forEach(System.out::println);

        //d)
        double suma=studentiCuNote.stream().mapToDouble(Student::getNota).sum();
        System.out.println("Suma notelor: "+suma);

        //e)
        double media=suma/studentiCuNote.size();
        System.out.println("Media: "+media);

    }

        public static double gasesteNota (String prenume, String nume, Map < Integer, Student > tineri){
            Map<String, Student> mapNume = new HashMap<>();
            for (Student s : tineri.values()) {
                String cheie = s.getPrenume() + "-" + s.getNume();
                mapNume.put(cheie, s);

            }

            String cheieCautata = prenume + "-" + nume;
            Student s = mapNume.get(cheieCautata);

            if (s != null) {
                return s.getNota();
            }
            return 0.0;


        }
        public static void salveazaInFisier (String numeFisier, Collection < ? extends Student > colectie){
            List<String> lines = new ArrayList<>();

            for (Student s : colectie) {
                lines.add(s.toString());
            }

            try {
                Files.write(Paths.get(numeFisier), lines);
                System.out.println("Salvat in fisier: " + numeFisier);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



}

