package animals;


import data.AnimalTypeData;

public abstract class AbsAnimal {

    private int id = 0;
    private String name = "";
    private int age = -1;
    private float weight = -1;
    private String color = null;
    private AnimalTypeData animalType;



    public AnimalTypeData getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalTypeData animalType) {
        this.animalType = animalType;
    }
    public long getId() {
        return this.id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }



    public abstract void say();

    @Override
    public String toString() {
        return String.format("Привет! меня зовут %s, мне %d %s, я вешу - %.1f кг, мой цвет - %s",
                name, age, getAgeCase(), weight, color.toLowerCase());
    }

    private String getAgeCase() {
        float remainder = age % 10;

        if (age >= 11 && age <= 19) {
            return "лет";
        }

        if (remainder == 1) {
            return "год";
        }

        if (remainder >= 2 && remainder <= 4) {
            return "года";
        }

        return "лет";
    }

}
