import org.junit.jupiter.api.Test;
import ro.ulbs.proiectaresoftware.students.AplicatieCuBursa;
import ro.ulbs.proiectaresoftware.students.StudentBursier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AplicatieCuBursaTest {
    @Test
    void testSorteaza(){
        AplicatieCuBursa ap = new AplicatieCuBursa();
        List<StudentBursier> lista=ap.genereaza();
        List<StudentBursier> sortat=ap.sorteaza(lista);

        for(int i=0;i<sortat.size()-1;i++){
            StudentBursier  a=sortat.get(i);
            StudentBursier  b=sortat.get(i+1);

            int cmp = a.getFormatieDeStudiu().compareTo(b.getFormatieDeStudiu());
            if(cmp==0){cmp=a.getNume().compareTo(b.getNume());}
            if(cmp==0){cmp=a.getPrenume().compareTo(b.getPrenume());}
            if(cmp==0){cmp=Double.compare(a.getNota(),b.getNota());}
            if(cmp==0){cmp=Double.compare(a.getCuantumBursa(),b.getCuantumBursa());}
            assertTrue(cmp <= 0, "Lista NU este sortată corect!");

        }
    }
}
