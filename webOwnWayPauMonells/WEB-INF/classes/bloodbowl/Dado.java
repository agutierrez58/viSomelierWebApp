package bloodbowl;

public class Dado {
    public static int tiraDado(int caras) {
        return (int)(caras*Math.random())+1;
    }
}
