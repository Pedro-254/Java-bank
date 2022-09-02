package banco;

import java.io.IOException;
import java.util.Scanner;

public class Banco {

    public static void main(String[] args) throws IOException {
        boolean cont = true;
        while (cont){
//            Menu menu = new Menu();
            Menu menu = new Menu();
            menu.print();

            Scanner s = new Scanner(System.in);
            cont =  Menu.opt(s.nextInt());
        } 

    }

}
