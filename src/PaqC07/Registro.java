package PaqC07;

import java.io.Serializable;
import java.util.Arrays;

public class Registro implements Serializable {
    private int numHab, numPisos;
    protected Cliente [][] habitaciones;

    public Registro() {
        numPisos = 8;
        numHab = 6;
        habitaciones = new Cliente[numPisos][numHab];
    }

    public String muestraPantalla(){
        String str = "";
        for(int i = 0; i < this.numPisos; i++){
            for(int j = 0; j< this.numHab; j++){
                if(this.habitaciones[i][j] == null){
                    str += " L ";
                }else str += " R ";
            }
            str += "\n";
        }
        return str;
    }

    public int [] encuentraHab(int tipo){
        int [] coordenadas = new int [2];
        if(tipo == 1) coordenadas = búsquedaHab(0,5,coordenadas);
        else if (tipo == 2) coordenadas = búsquedaHab(5,7,coordenadas);
        else if (tipo == 3) coordenadas =  búsquedaHab(7,8,coordenadas);
        return coordenadas;
    }

    public int [] búsquedaHab(int indInf, int indSup, int [] coordenadas){
        for(int i = indInf; i < indSup; i++){
            for(int j = 0; j < numHab; j++){
                if(this.habitaciones[i][j] == null){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public void almacenaReserva(int [] coordenadas, String nombre, String apellidos, int dni, int teléfono, int numTarjeta, String alta, String baja, String régimen){
        habitaciones[coordenadas[0]][coordenadas[1]].setNombre(nombre);
        habitaciones[coordenadas[0]][coordenadas[1]].setBaja(baja);
        habitaciones[coordenadas[0]][coordenadas[1]].setAlta(alta);
        habitaciones[coordenadas[0]][coordenadas[1]].setApellidos(apellidos);
        habitaciones[coordenadas[0]][coordenadas[1]].setTeléfono(teléfono);
        habitaciones[coordenadas[0]][coordenadas[1]].setRégimen(régimen);
        habitaciones[coordenadas[0]][coordenadas[1]].setNumTarjeta(numTarjeta);
        habitaciones[coordenadas[0]][coordenadas[1]].setDni(dni);
    }

    public void anulaReserva(int [] coordenadas){
        if (habitaciones[coordenadas[0]][coordenadas[1]] != null)
            habitaciones[coordenadas[0]][coordenadas[1]] = null;
        else
            System.out.println("No existe reserva en la habitación " + (coordenadas[1]+1)+ " de la planta " + (coordenadas[0]+1) + ".");
    }
    public String verDatosCliente(int [] c){
        if (habitaciones[c[0]][c[1]] != null)
            return habitaciones[c[0]][c[1]].toString();
        else
            return "No existe reserva en la habitación " + (c[1]+1)+ " de la planta " + (c[0]+1) + ".";
    }
    public int[] buscarReserva(int dni){
        for (int i = 0; i < numPisos; i++)
            for (int j = 0; j < numHab; j++)
                if (habitaciones[i][j] != null)
                    if (habitaciones[i][j].getDni() == dni)
                        return new int[]{i, j};
        return new int[] {-1,-1};
    }

}



