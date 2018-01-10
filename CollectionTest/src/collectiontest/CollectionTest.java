package collectiontest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAMAR ALI El-Fartouni
 */
public class CollectionTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> course = new ArrayList<>();

        //Ajout d'éléments au tableau
        course.add("Du thé");
        course.add("Du pain");
        course.add("Des oeufs"+"\n");

        //Affichage du deuxième élément 
        System.out.println(course.get(1));
        //Nombre d'éléments dans la liste
        int nbElement = course.size();
        //Boucle sur chaque élément de la liste
        for (int i = 0; i < nbElement; i++) {
            System.out.println(course.get(i));
        }
        //Boucle améliorée sans indice incrémenté
        for (String element : course) {
            System.out.println(element);
        }
        //Boucle avec un itérateur
        for (Iterator<String> iterator = course.iterator(); iterator.hasNext();) {
            String next = iterator.next();
            System.out.println(next);
        }

        System.out.println("Gestion des set"+"\n");
        Set<String> languages = new TreeSet<>();

        languages.add("Java");
        languages.add("C#");
        languages.add("Ada");
        languages.add("Java"+"\n");

        for (String language : languages) {
            System.out.println(language);
        }

        System.out.println("Gestion des map"+"\n");
        Map<String, String> person = new HashMap<>();

        person.put("Nom", "DAMAR ALI"+"\n");
        person.put("Prénom", "El-Fartouni"+"\n");
        person.put("age", "24"+"\n");

        //Affichage d'une clef
        System.out.println(person.get("Nom"));

        Set keyList = person.keySet();

        for (Object key : keyList) {
            System.out.println(key.toString());
            System.out.println(person.get(key.toString()));
        }

        Collection<String> personInfo = person.values();

        for (String item : personInfo) {
            System.out.println(item);
        }

        System.out.println("List de Map");

        List<Map<String, String>> students = new ArrayList<>();

        Map<String, String> student = new HashMap<>();
        student.put("name", "Lovelace");
        student.put("firstName", "Ada");
        student.put("sex", "F");

        students.add(student);

        student = new HashMap<>();
        student.put("name", "Pascal");
        student.put("firstName", "Blaise");
        student.put("sex", "M");

        students.add(student);

        student = new HashMap<>();
        student.put("name", "Kant");
        student.put("firstName", "Emmanuel");
        student.put("sex", "M");

        students.add(student);

        Map<String, String> item;
        for (int i = 0; i < students.size(); i++) {
            item = students.get(i);
            StringBuilder sb = new StringBuilder();

            sb.append("Bonjour ");

            if (item.get("sex").equals("F")) {
                sb.append("madame ");
            } else {
                sb.append("monsieur ");
            }

            sb.append(item.get("firstName"));
            sb.append(" ");
            sb.append(item.get("name"));

            System.out.println(sb.toString());
        }

        String civilite;
        for (Map<String, String> element : students) {
            if (element.get("sex") == "F") {
                civilite = "madame";
            } else {
                civilite = "monsieur";
            }

            System.out.println(
                    "Bonjour "
                    + civilite + " "
                    + element.get("firstName")
                    + " " + element.get("name")
            );
        }

        //Liste des étudiants avec une approche plus orientée objet
        List<Student> studentList = new ArrayList<>();

        //Remplissage de la liste
        studentList.add(new Student("Pascal", "Blaise", 'M'));
        studentList.add(new Student("Lovelace", "Ada", 'F'));
        studentList.add(new Student("Stark", "Arya", 'F'));

        //Affichage de la liste
        for (Student st : studentList) {
            st.greet();
        }

        try {
            //Test de la persistance avec le composant DAO
            Connection cn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/bibliotheque",
                    "root",
                    ""
            );

            //Instanciation du DTO
            Student tycho = new Student("Brahé", "Tycho", 'M');

            //Persistance du DTO
            //Instanciation du DAO
            StudentDAO dao = new StudentDAO(cn);

            dao.save(tycho);
        } catch (SQLException ex) {
            Logger.getLogger(CollectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
