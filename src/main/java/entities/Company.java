package entities;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.persistence.*;

import java.io.ByteArrayInputStream;
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
    @Transient
    private StreamedContent outputImage;

    public StreamedContent getOutputImage() {
        return outputImage;
    }

    public void setOutputImage(StreamedContent outputImage) {
        this.outputImage = outputImage;
    }

    public StreamedContent getConvertedImage() {
        StreamedContent img = new DefaultStreamedContent(new ByteArrayInputStream(image), "image/png");
        return img;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

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
