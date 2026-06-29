package service;

import enums.EstadoTicket;
import model.*;

public interface ServicioTickets {
    Ticket crearTicket();
    Ticket buscarTicket(String identificador);
    void cerrarTicket(String identificador);
    void listarTickets();
    void asignarTecnico(Ticket ticket, Tecnico tecnico);
    void filtrarPorEstado(EstadoTicket estado);
}
