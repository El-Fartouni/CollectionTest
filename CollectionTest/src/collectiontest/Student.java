/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectiontest;

/**
 *
 * @author DAMAR ALI El-Fartouni
 */
public class Student {

    private String name;

    private String firstName;

    private char sex;

    private Integer id;

    public Student() {
    }

    public Student(String name, String firstName, char sex) {
        this.name = name;
        this.firstName = firstName;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getCivilite() {
        String civilite;
        if (this.sex == 'F') {
            civilite = "madame";
        } else {
            civilite = "monsieur";
        }

        return civilite;
    }

    public void greet() {
        System.out.println("Bonjour " + this.getCivilite()
                + " " + this.firstName + " " + this.name);
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return firstName + " " + name;
    }
    
}
