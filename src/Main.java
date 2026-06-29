import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import exceptions.EmptyListException;
import utils.MyUtils;
import utils.Colors;
import service.impl.SistemaTickets;
import model.*;
import enums.*;

public class Main {
    private static void mostrarMenu(){
        System.out.println("\n====== MESA DE SOPORTE ======");
        System.out.println("1. Registrar empleado");
        System.out.println("2. Registrar técnico");
        System.out.println("3. Crear ticket");
        System.out.println("4. Asignar técnico");
        System.out.println("5. Cambiar estado");
        System.out.println("6. Agregar etiqueta");
        System.out.println("7. Buscar ticket");
        System.out.println("8. Listar ticket");
        System.out.println("9. Salir");
        System.out.println();

    }
    public static void main(String[] args){
        SistemaTickets sys = new SistemaTickets();
        boolean ok = false;
        int opcion = 0;
        do{
            ok = false;
            mostrarMenu();
            opcion = MyUtils.pedirDato("Opción", Integer.class, integer -> integer <= 9 && integer >= 1);
            try{
                do{
                    try{
                        switch(opcion){
                            case 1 -> {
                                sys.registrarEmpleado();
                                ok = true;
                            }
                            case 2 -> {
                                sys.registrarTecnico();
                                ok = true;
                            }
                            case 3 -> {
                                sys.crearTicket();
                                ok = true;
                            }
                            case 4 -> {
                                if(sys.getListTickets() == null) throw new EmptyListException("No existe ningún ticket aún, agregue alguno");
                                if(sys.getListTecnicos() == null) throw new EmptyListException("No existe ningún técnico aún, agregue alguno");
                                String identificador = MyUtils.pedirDato("Ingrese el nombre, ID o descripción del ticket", String.class, s -> !s.trim().isEmpty() && sys.buscarTicket(s) != null);
                                Ticket ticket = sys.buscarTicket(identificador);
                                String identificadorTecnico = MyUtils.pedirDato("Ingrese el nombre o ID del técnico", String.class, s -> !s.trim().isEmpty() && sys.buscarTecnico(s) != null);
                                Tecnico tecnico = sys.buscarTecnico(identificadorTecnico);
                                ticket.setTecnicoAsignado(tecnico);
                                ok = true;
                            }
                            case 5 -> {
                                if(sys.getListTickets() == null) throw new EmptyListException("No existe ningún ticket aún, agregue alguno");
                                String identificador = MyUtils.pedirDato("Ingrese el nombre, ID o descripción del ticket", String.class, s -> !s.trim().isEmpty() && sys.buscarTicket(s) != null);
                                Ticket ticket = sys.buscarTicket(identificador);
                                if(ticket.getEstado() == EstadoTicket.CERRADO) throw new IllegalStateException("El ticket ya estaba cerrado");
                                EstadoTicket estado = MyUtils.pedirEnum("Ingrese el estado del ticket", EstadoTicket.class);
                                ticket.setEstado(estado);
                                ok = true;
                            }
                            case 6 ->{
                                if(sys.getListTickets() == null) throw new EmptyListException("No existe ningún ticket aún, agregue alguno");
                                String identificador = MyUtils.pedirDato("Ingrese el nombre, ID o descripción del ticket", String.class, s -> !s.trim().isEmpty() && sys.buscarTicket(s) != null);
                                Ticket ticket = sys.buscarTicket(identificador);
                                String entrada;
                                List<String> etiquetas = new ArrayList<>();
                                do{
                                    entrada = MyUtils.pedirStringOpcional("Ingrese las etiquetas del ticket (para salir deje en blanco)");
                                    if(entrada != null) etiquetas.add(entrada);
                                } while(entrada != null);
                                for(String etiqueta : etiquetas){
                                    ticket.addEtiqueta(etiqueta);
                                }
                                ok = true;
                            }
                            case 7 -> {
                                if(sys.getListTickets() == null) throw new EmptyListException("No existe ningún ticket aún, agregue alguno");
                                String identificador = MyUtils.pedirStringOpcional("Ingrese el nombre, ID o descripción del ticket (deje en blanco para cancelar)");
                                if(identificador == null) ok = true;

                                Ticket t = sys.buscarTicket(identificador);
                                if(identificador == null) System.out.println("No existe el ticket"); ok = true;

                                System.out.printf("===\nID: %s | Título: %s | Descripción: %s | Estado: %s | Prioridad: %s \n Empleado solicitante: %s (ID: %s) \n Técnico asignado: %s (ID: %s)\n Etiquetas: \n", t.getId(), t.getTitulo(), t.getDescripcion(), t.getEstado().toString(), t.getPrioridad().toString(), t.getSolicitante().getNombre(), t.getSolicitante().getId(), t.getTecnicoAsignado().getNombre(), t.getTecnicoAsignado().getId());
                                for(String etiqueta : t.getEtiquetas()){
                                    System.out.println(etiqueta);
                                }
                                System.out.printf("\n===\n");
                                ok = true;
                            }
                            case 8 -> {
                                sys.listarTickets();
                                ok = true;
                            }
                            case 9 -> {
                                System.out.println(Colors.GREEN + "Gracias por usar el sistema" + Colors.RESET);
                                Thread.sleep(1000);
                                System.out.println(Colors.GREEN + "¡Hasta luego!" + Colors.RESET);
                                Thread.sleep(1000);
                                ok = true;
                            }
                        }
                    } catch(Exception e){
                        System.out.println(Colors.RED + e.getMessage() + Colors.RESET);
                        break;
                    }
                } while(!ok);
            } catch (Exception e){
                System.out.println(Colors.RED + e.getMessage() + Colors.RESET);
            }
        } while(opcion != 9);

    }
}
