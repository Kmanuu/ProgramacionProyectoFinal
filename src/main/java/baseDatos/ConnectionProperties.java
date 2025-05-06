package baseDatos;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "connection")
public class ConnectionProperties {

    private String driver;
    private String url;
    private String username;
    private String password;

    @XmlElement
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    @XmlElement
    public String getUrl() {  // ← minúscula aquí
        return url;
    }

    public void setUrl(String url) {  // ← minúscula aquí
        this.url = url;
    }


    @XmlElement
    public String getUser() {
        return username;
    }

    public void setUser(String username) {
        this.username = username;
    }

    @XmlElement
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
