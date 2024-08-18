package data;

public enum ColorData {

    WHITE ("Белый"),
    BLACK ("Чёрный"),
    GREY ("Серый"),
    RED ("Рыжий"),
    BEIGE ("Бежевый"),
    BROWN ("Коричневый");

    private String name;
    ColorData(String name) {
        this.name = name;
    }

    public String getColor(){
        return name;
    }
}
