package model;

import java.util.HashSet;
import java.util.Set;

import enums.*;
import exceptions.*;

public class Ticket {
    // Atributos
    private String id;
    private String titulo;
    private String descripcion;
    private EstadoTicket estado;
    private Prioridad prioridad;
    private Empleado solicitante;
    private Tecnico tecnicoAsignado;
    private Set<String> etiquetas;

    // Getters
    public String getId(){
        return this.id;
    }
    public String getTitulo(){
        return this.titulo;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    public EstadoTicket getEstado(){
        return this.estado;
    }
    public Prioridad getPrioridad(){
        return this.prioridad;
    }
    public Empleado getSolicitante(){
        return this.solicitante;
    }
    public Tecnico getTecnicoAsignado(){
        return this.tecnicoAsignado;
    }
    public Set<String> getEtiquetas(){
        return this.etiquetas;
    }

    // Setters
    public void setId(String id){
        if(id != null && !id.isBlank()){
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID inválido");
        }
    }
    public void setTitulo(String titulo){
        if(titulo != null && !titulo.isBlank()){
            this.titulo = titulo;
        } else {
            throw new IllegalArgumentException("Título inválido");
        }
    }
    public void setDescripcion(String descripcion){
        if(descripcion != null && !descripcion.isBlank()){
            this.descripcion = descripcion;
        } else {
            throw new IllegalArgumentException("Descripción inválida");
        }
    }
    public void setEstado(EstadoTicket estado){
        if(this.estado == estado){
            throw new IllegalArgumentException("Estado inválido, ya estaba seleccionado");
        }
        this.estado = estado;
    }
    public void setPrioridad(Prioridad prioridad){
        if(this.prioridad == prioridad){
            throw new IllegalArgumentException("Prioridad inválida, ya estaba seleccionada");
        }
        this.prioridad = prioridad;
    }
    public void setSolicitante(Empleado solicitante){
        if(solicitante == null){
            throw new IllegalArgumentException("Solicitante inválido");
        }
        this.solicitante = solicitante;
    }
    public void setTecnicoAsignado(Tecnico tecnico){
        if(tecnico == null){
            throw new IllegalArgumentException("Técnico inválido");
        }
        this.tecnicoAsignado = tecnico;
    }
    public void addEtiqueta(String etiqueta){
        if(etiqueta != null && !etiqueta.isBlank()){
            this.etiquetas.add(etiqueta);
        } else {
            throw new IllegalArgumentException("Etiqueta inválida");
        }
    }

    // Constructor
    public Ticket(){
        this.etiquetas = new HashSet<>();
    }

    // Métodos
    public void cerrar(){
        if(this.estado == EstadoTicket.CERRADO){
            throw new IllegalStateException("El ticket ya se encuentra cerrado");
        }
        this.estado = EstadoTicket.CERRADO;
    }
    public void eliminarEtiqueta(String etiqueta) throws ElementNotFoundException {
        boolean eliminado = this.etiquetas.remove(etiqueta);
        if(!eliminado) throw new ElementNotFoundException("La etiqueta no existe");
    }

}
