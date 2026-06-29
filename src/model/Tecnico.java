package model;

public class Tecnico extends Empleado{
    private String especialidad;

    // Setter
    public void setEspecialidad(String especialidad){
        if(especialidad != null && !especialidad.isBlank()){
            this.especialidad = especialidad;
        } else {
            throw new IllegalArgumentException("Especialidad inválida");
        }
    }

    // Getter
    public String getEspecialidad(){
        return this.especialidad;
    }

    public Tecnico(String id, String nombre, String departamento, String especialidad){
        super(id, nombre, departamento);
        this.setEspecialidad(especialidad);
    }
    public Tecnico(){
        super();
        this.setEspecialidad("Sin especialidad");
    }
}
