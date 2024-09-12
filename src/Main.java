import entity.Banco;
import entity.Menu;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Menu menu = new Menu(banco);
        menu.mostrarMenu();
    }
}
