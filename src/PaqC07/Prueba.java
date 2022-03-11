package PaqC07;

import javax.management.MBeanRegistration;
import java.io.*;
import java.util.Scanner;


public class Prueba  {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Registro r1 = Leer();

        int sel = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("-----------MENÚ-----------\n1.Hacer reserva\n2.Anular reserva\n3.Mostrar mapa\n" +
                    "4.Ver datos de una reserva\n5.Buscar una reserva por DNI\n6.Salir");
            System.out.println("Elige una opción: ");
            sel = sc.nextInt();
            switch (sel){
                case 1:
                    hacerResereva(r1);
                    break;
                case 2:
                    System.out.println("Introduce la planta y habitación donde tienes la reserva: ");
                    int [] coordenadas = new int[2];
                    coordenadas[0] = sc.nextInt() -1;
                    coordenadas[1] = sc.nextInt() - 1;
                    r1.anulaReserva(coordenadas);
                    System.out.println(r1.muestraPantalla());
                    Serializar(r1);
                    break;
                case 3:
                    System.out.println(r1.muestraPantalla());
                    break;
                case 4:
                    System.out.println("Indica la planta y habitación a inspeccionar: ");
                    int [] c = new int[2];
                    c[0] = sc.nextInt() -1;
                    c[1] = sc.nextInt() - 1;
                    System.out.println(r1.verDatosCliente(c));
                    break;
                case 5:
                    System.out.println("Introduce el DNI de la reserva: ");
                    int d = sc.nextInt();
                    int [] s = r1.buscarReserva(d);
                    if (s[0] != -1)
                        System.out.println("Tu reserva está en la planta " + (s[0]+1) + " habitación " + (s[1]+1) + ".");
                    else
                        System.out.println("No existe reserva con el dni " + d);
                    break;
                case 6: break;
                default:
                    System.out.println("ERROR: Elige una opción del menu.");
                    break;
            }
        }while (sel != 6);
        return;


    }

    private static void Serializar(Registro r) throws IOException{
        FileOutputStream fos = new FileOutputStream("reg.dat");
        ObjectOutputStream salida = new ObjectOutputStream(fos);
        salida.writeObject(r);
        fos.close();
        salida.close();
    }

    private static Registro Leer() throws IOException, ClassNotFoundException {
        if (exists("reg.dat") == true){
            FileInputStream fis = new FileInputStream("reg.dat");
            ObjectInputStream entrada = new ObjectInputStream(fis);
            Registro salida = (Registro) entrada.readObject();
            fis.close();
            entrada.close();
            return salida;
        }
        else{
            return new Registro();
        }
    }

    private static Boolean exists(String name){
        File tempFile = new File(name); //crea un archivo temporal para comprobar si existe o no
        return tempFile.exists();   //devuelve true o false
    }
    private static void hacerResereva(Registro r1) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿De qué tipo es la habitación? (1-Estándar, 2-Balcón, 3-Suite)");
        int res = sc.nextInt();
        int [] coordenadas = r1.encuentraHab(res);
        if (coordenadas[0] == -1) {     //Si no hay hueco disponible para ese tipo de habitación, la función búsquedaHab devuelve un vector {-1,-1}
            System.out.println("No hay habitaciones disponibles.");
        }
        else {
            System.out.println("NOMBRE: ");
            sc.nextLine();
            String nombre = sc.nextLine();
            System.out.println("APELLIDOS: ");
            String apellidos = sc.nextLine();
            System.out.println("DNI/NIF: ");
            int dni = sc.nextInt();
            System.out.println("TELÉFONO: ");
            int tel = sc.nextInt();
            System.out.println("Tarjeta de Pago: ");
            int card = sc.nextInt();
            System.out.println("\nFECHA DE ALTA:");
            sc.nextLine();
            String alta = sc.nextLine();
            System.out.println("FECHA DE BAJA:");
            String baja = sc.nextLine();
            System.out.println("Régimen: ");
            String Régimen = sc.nextLine();
            r1.habitaciones[coordenadas[0]][coordenadas[1]] = new Cliente();
            r1.almacenaReserva(coordenadas, nombre, apellidos, dni, tel, card, alta, baja, Régimen);
        }
        System.out.println(r1.muestraPantalla());
        Serializar(r1);
    }
}


