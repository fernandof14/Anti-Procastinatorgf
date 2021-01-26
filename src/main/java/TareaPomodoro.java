


public class TareaPomodoro {
    private String nombreTarea;
    private boolean estadoFinalizacion;

    public TareaPomodoro(String name, boolean condicion){
        this.nombreTarea = name;
        this.estadoFinalizacion = false;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public boolean isEstado() {
        return estadoFinalizacion;
    }

    public void setEstado(boolean estado) {
        this.estadoFinalizacion = estado;
    }
}
