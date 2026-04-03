package ro.ulbs.proiectaresoftware.students;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
                    s.setNota(nota);
                }
            }
            System.out.println("\nStudenti cu note:");

            for (Student s : mapStudenti.values()) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

