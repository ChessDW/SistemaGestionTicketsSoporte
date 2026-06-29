package model;

import java.util.UUID;

public class Empleado {
    // Atributos
    private String id;
    private String nombre;
    private String departamento;

    // Setters
    public void setId(String id){
        if(id != null && !id.isBlank()){
            this.id = id;
        } else {
            throw new IllegalArgumentException("Identificador inválido");
        }
    }
    public void setNombre(String nombre){
        if(nombre != null && !nombre.isBlank()){
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("Nombre del empleado inválido");
        }
    }
    public void setDepartamento(String departamento){
        if(departamento != null && !departamento.isBlank()){
            this.departamento = departamento;
        } else {
            throw new IllegalArgumentException("Departamento del empleado inválido");
        }
    }

    // Getters
    public String getId(){
        return this.id;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getDepartamento(){
        return this.departamento;
    }

    public Empleado(String id, String nombre, String departamento){
        this.setId(id);
        this.setNombre(nombre);
        this.setDepartamento(departamento);
    }
    public Empleado(){
        this(UUID.randomUUID().toString(), "Sin nombre", "Sin departamento");
    }
}
