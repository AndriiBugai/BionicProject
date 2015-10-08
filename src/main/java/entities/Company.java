package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by strapper on 07.10.15.
 */
@Entity
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private String descripton;
    private byte[] image;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return descripton;
    }
    public void setDescription(String description) {
        this.descripton = description;
    }
    public  byte[] getImage() {
        return image;
    }
    public void setImage( byte[] image) {
        this.image = image;
    }
}
