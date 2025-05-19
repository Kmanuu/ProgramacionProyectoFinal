package baseDatos;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

// Esta clase sirve para guardar los datos de conexión que se leen desde el archivo XML
@XmlRootElement(name = "connection")
public class ConnectionProperties {

    // Atributos que vamos a rellenar con los valores del XML
    private String driver;
    private String url;
    private String username;
    private String password;

    // Getter y setter para el driver
    @XmlElement
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    // Getter y setter para la URL de la base de datos
    @XmlElement
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // Getter y setter para el usuario (username)
    @XmlElement
    public String getUser() {
        return username;
    }

    public void setUser(String username) {
        this.username = username;
    }

    // Getter y setter para la contraseña
    @XmlElement
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
