package model;

public class Person {
    protected int id;
    protected String name;
    protected String gender;
    protected int age;
    protected String inscription;
    protected String image;

    public Person() {}

    public Person(String name, String gender, int age, String inscription, String image) {
        super();
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.inscription = inscription;
        this.image = image;
    }

    public Person(int id, String name, String gender, int age, String inscription, String image) {
        super();
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.inscription = inscription;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getInscription() {
        return inscription;
    }

    public void setInscription(String inscription) {
        this.inscription = inscription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}