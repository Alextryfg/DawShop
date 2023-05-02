public class Users {

    private String nombreUser;
    private String correoUser;
    private String numeroTarjetaUser;
    private String passwordUser;

    public Users(String nombreUser, String correoUser, String numeroTarjetaUser, String passwordUser){

        this.nombreUser = nombreUser;
        this.correoUser = correoUser;
        this.numeroTarjetaUser = numeroTarjetaUser;
        this.passwordUser = passwordUser;

    }

    public String getNombreUser(){
        return this.nombreUser;
    }

    public String getCorreoUser(){
        return this.correoUser;
    }

    public String getNumeroTarjetaUser(){
        return this.numeroTarjetaUser;
    }

    public void setNombreUser(String nombreUser){
        this.nombreUser = nombreUser;
    }

    public void setCorreoUser(String correoUser){
        this.correoUser = correoUser;
    }

    public void setNumeroTarjetaUser(String numeroTarjetaUser){
        this.numeroTarjetaUser = numeroTarjetaUser;
    }

    public String getPasswordUser(){
        return this.passwordUser;
    }

    public void setPasswordUser(String passwordUser){
        this.passwordUser = passwordUser;
    }
    
}
