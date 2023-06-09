public class Users {

    private String nombre;
    private String correo;
    private String numeroTarjeta;
    private String tipoTarjeta;
    private String password;

    //Constructor

    public Users(String nombre, String correo, String tipoTarjeta, String numeroTarjeta, String password){

        this.nombre = nombre;
        this.correo = correo;
        this.tipoTarjeta = tipoTarjeta;
        this.numeroTarjeta = numeroTarjeta;
        this.password = password;

    }

    //Getters y setters de los atributos

    public String getNombre(){
        return this.nombre;
    }

    public String getCorreo(){
        return this.correo;
    }

    public String getNumeroTarjeta(){
        return this.numeroTarjeta;
    }

    public String getTipoTarjeta(){
        return this.tipoTarjeta;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public void setNumeroTarjeta(String numeroTarjeta){
        this.numeroTarjeta = numeroTarjeta;
    }

    
    public void setTipoTarjeta(String tipoTarjeta){
        this.tipoTarjeta=tipoTarjeta;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String passwordUser){
        this.password = passwordUser;
    }
    
}
