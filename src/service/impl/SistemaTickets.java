package service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Scanner;

import enums.EstadoTicket;
import model.*;
import service.ServicioTickets;
import utils.MyUtils;
import enums.*;
import exceptions.*;

public class SistemaTickets implements ServicioTickets {
    private List<Ticket> tickets;
    private List<Tecnico> tecnicos;
    private List<Empleado> empleados;
    private final static Scanner sc = new Scanner(System.in);


    public SistemaTickets(){
        this.tickets = new ArrayList<>();
        this.tecnicos = new ArrayList<>();
        this.empleados = new ArrayList<>();
    }

    public List<Ticket> getListTickets(){
        if(this.tickets.isEmpty()) return null;
        return this.tickets;
    }
    public List<Tecnico> getListTecnicos(){
        if(this.tecnicos.isEmpty()) return null;
        return this.tecnicos;
    }
    public List<Empleado> getListEmpleados(){
        if(this.empleados.isEmpty()) return null;
        return this.empleados;
    }

    // Métodos
    public Empleado buscarEmpleado(String identificador){
        if(this.empleados.isEmpty()){
            throw new EmptyListException("No existe ningún empleado aún, registre alguno");
        }

        // buscar por id
        for(Empleado empleado : this.empleados){
            if(empleado.getId().equals(identificador)){
                return empleado;
            }
        }
        // buscar por nombre
        for(Empleado empleado : this.empleados){
            if(empleado.getNombre().equals(identificador)){
                return empleado;
            }
        }
        return null;
    }
    public Tecnico buscarTecnico(String identificador){
        if(this.tecnicos.isEmpty()){
            throw new EmptyListException("No existe ningún técnico aún, registre alguno");
        }

        // Buscar por id
        for(Tecnico tecnico : this.tecnicos){
            if(tecnico.getId().equals(identificador)){
                return tecnico;
            }
        }

        // Buscar por nombre
        for(Tecnico tecnico: this.tecnicos){
            if(tecnico.getNombre().equals(identificador)){
                return tecnico;
            }
        }
        return null;
    }
    public Ticket buscarTicket(String identificador){
        if(this.tickets.isEmpty()){
            throw new EmptyListException("No existe ningún ticket aún, considere crear alguno");
        }

        // buscar por id
        for(Ticket ticket : this.tickets){
            if(ticket.getId().equals(identificador)){
                return ticket;
            }
        }
        // buscar por título
        for(Ticket ticket : this.tickets){
            if(ticket.getTitulo().equals(identificador)){
                return ticket;
            }
        }
        // buscar por descripcion
        for(Ticket ticket : this.tickets){
            if(ticket.getDescripcion().equalsIgnoreCase(identificador)){
                return ticket;
            }
        }
        return null;
    }

    public void registrarEmpleado(){
        String id = MyUtils.pedirStringOpcional("Ingrese el ID del empleado (déjelo en blanco para uno genérico)"); // manejar después UUID
        String nombre = MyUtils.pedirDato("Ingrese el nombre del empleado", String.class, s -> !s.trim().isEmpty());
        String departamento = MyUtils.pedirDato("Ingrese el departamento del empleado", String.class, s -> !s.trim().isEmpty());

        if(id == null){
            id = UUID.randomUUID().toString();
            System.out.println("ID genérico: " + id);
        }

        Empleado empleado = new Empleado(id, nombre, departamento);

        this.empleados.add(empleado);
    }
    public void registrarTecnico(){
        String id = MyUtils.pedirStringOpcional("Ingrese el ID del técnico (déjelo en blanco para uno genérico)"); // manejar después UUID
        String nombre = MyUtils.pedirDato("Ingrese el nombre del técnico", String.class, s -> !s.trim().isEmpty());
        String departamento = MyUtils.pedirDato("Ingrese el departamento del técnico", String.class, s -> !s.trim().isEmpty());
        String especialidad = MyUtils.pedirDato("Ingrese la especialidad del técnico", String.class, s -> !s.trim().isEmpty());

        if(id == null){
            id = UUID.randomUUID().toString();
            System.out.println("ID genérico: " + id);
        }

        Tecnico tecnico = new Tecnico(id, nombre, departamento, especialidad);

        this.tecnicos.add(tecnico);
    }
    public Ticket crearTicket() {
        if(this.empleados.isEmpty()){
            throw new IllegalStateException("No existen empleados aún, registre alguno");
        }
        if(this.tecnicos.isEmpty()){
            throw new IllegalStateException("No existen técnicos aún, registre alguno");
        }

        // Datos del ticket básicos
        String id = MyUtils.pedirStringOpcional("Ingrese el ID para el ticket (déjelo en blanco para uno genérico)");
        String nombre = MyUtils.pedirDato("Ingrese el título para el ticket", String.class, s -> !s.trim().isEmpty());
        String descripcion = MyUtils.pedirDato("Ingrese la descripción para el ticket", String.class, s -> !s.trim().isEmpty());
        EstadoTicket estado = MyUtils.pedirEnum("Ingrese el estado para el ticket", EstadoTicket.class);
        Prioridad prioridad = MyUtils.pedirEnum("Ingrese la prioridad del ticket", Prioridad.class);

        Empleado empleadoSolicitante = null;

        // Datos del empleado solicitante
        while(true){
            try{
                String identificadorEmpleado = MyUtils.pedirDato("Ingrese el nombre o ID del empleado solicitante", String.class, s -> !s.trim().isEmpty());
                if(buscarEmpleado(identificadorEmpleado) == null){
                    throw new IllegalArgumentException("El empleado no existe, ingrese de nuevo el nombre o ID del empleado");
                }
                empleadoSolicitante = buscarEmpleado(identificadorEmpleado);
                break;
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        Tecnico tecnicoAsignado = null;

        // Datos del técnico asignado
        while(true){
            try{
                String identificadorTecnico = MyUtils.pedirDato("Ingrese el nombre o ID del técnico asignado", String.class, s -> !s.trim().isEmpty());
                if(buscarTecnico(identificadorTecnico) == null){
                    throw new IllegalArgumentException("El técnico no existe, ingrese de nuevo el nombre o ID del técnico");
                }
                tecnicoAsignado = buscarTecnico(identificadorTecnico);
                break;
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }



        // Etiquetas
        String entrada;
        List<String> etiquetas = new ArrayList<>();
        do{
            entrada = MyUtils.pedirStringOpcional("Ingrese las etiquetas del ticket (para salir deje en blanco)");
            if(entrada != null) etiquetas.add(entrada);
        } while(entrada != null);

        if(id == null){
            id = UUID.randomUUID().toString();
            System.out.println("ID genérica: " + id);
        }
        // Instancia del ticket
        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setTitulo(nombre);
        ticket.setDescripcion(descripcion);
        ticket.setEstado(estado);
        ticket.setPrioridad(prioridad);
        for(String etiqueta : etiquetas){
            ticket.addEtiqueta(etiqueta);
        }
        ticket.setSolicitante(empleadoSolicitante);
        ticket.setTecnicoAsignado(tecnicoAsignado);

        this.tickets.add(ticket);
        return ticket;
    }

    public void cerrarTicket(String identificador){
        Ticket ticket = buscarTicket(identificador);
        if(ticket == null) throw new IllegalArgumentException("No existe ningún ticket con la información dada");

        ticket.cerrar();
        System.out.println("Se ha cerrado el ticket con éxito");
    }

    public void listarTickets(){
        if(this.tickets.isEmpty()) throw new EmptyListException("No existe ningún ticket aún, considere crear alguno");
        int contador = 0;
        for(Ticket t : this.tickets){
            contador++;
            System.out.printf("===\n%d. ID: %s | Título: %s | Descripción: %s | Estado: %s | Prioridad: %s \n Empleado solicitante: %s (ID: %s) \n Técnico asignado: %s (ID: %s)\n Etiquetas: \n", contador, t.getId(), t.getTitulo(), t.getDescripcion(), t.getEstado().toString(), t.getPrioridad().toString(), t.getSolicitante().getNombre(), t.getSolicitante().getId(), t.getTecnicoAsignado().getNombre(), t.getTecnicoAsignado().getId());
            for(String etiqueta : t.getEtiquetas()){
                System.out.println(etiqueta);
            }
            System.out.printf("\n===\n");
        }
    }

    public void asignarTecnico(Ticket ticket, Tecnico tecnico){
        if(ticket == null || tecnico == null) throw  new IllegalArgumentException("Ticket o técnico con formato incorrecto");
        ticket.setTecnicoAsignado(tecnico);
    }

    public void filtrarPorEstado(EstadoTicket estado){
        if(this.tickets.isEmpty()) throw new EmptyListException("No existe ningún ticket aún, considere crear alguno");
        int contador = 0;
        System.out.println("=== TODOS LOS TICKETS CON EL ESTADO SELECCIONADO ===\n");
        for(Ticket t : this.tickets){
            if(t.getEstado() == estado){
                contador++;
                System.out.printf("===\n%d. ID: %s | Título: %s | Descripción: %s | Estado: %s | Prioridad: %s \n Empleado solicitante: %s (ID: %s) \n Técnico asignado: %s (ID: %s)\n Etiquetas: \n", contador, t.getId(), t.getTitulo(), t.getDescripcion(), t.getEstado().toString(), t.getPrioridad().toString(), t.getSolicitante().getNombre(), t.getSolicitante().getId(), t.getTecnicoAsignado().getNombre(), t.getTecnicoAsignado().getId());
                for(String etiqueta : t.getEtiquetas()){
                    System.out.println(etiqueta);
                }
                System.out.printf("\n===\n");
            }
        }
    }
}
