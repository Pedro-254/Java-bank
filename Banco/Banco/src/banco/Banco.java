package banco;

import java.io.IOException;
import java.util.Scanner;

public class Banco {

    public static void main(String[] args) throws IOException {
        boolean cont = true;
        while (cont){
//            Menu menu = new Menu();
            new_Menu menu = new new_Menu();
            menu.print();

            Scanner s = new Scanner(System.in);
            cont =  new_Menu.opt(s.nextInt());
        } 

    }

}
