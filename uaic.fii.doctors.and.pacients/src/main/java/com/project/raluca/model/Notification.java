package com.project.raluca.model;


import com.project.raluca.model.enums.NotificationType;

import java.util.Objects;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Notification")
public class Notification extends AbstractEntity  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;


    @ManyToOne
    @JoinColumn(name="pacient_id")
    private Pacient pacient;

    private String content;

    private Date createDate;

    private NotificationType type;

    private Boolean isSeen;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getSeen() {
        return isSeen;
    }

    public void setSeen(Boolean seen) {
        isSeen = seen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Notification)) {
            return false;
        }
        Notification that = (Notification) o;
        return getId() == that.getId() &&
                Objects.equals(getDoctor(), that.getDoctor()) &&
                Objects.equals(getPacient(), that.getPacient()) &&
                Objects.equals(getContent(), that.getContent()) &&
                Objects.equals(getCreateDate(), that.getCreateDate()) &&
                getType() == that.getType() &&
                Objects.equals(isSeen, that.isSeen);
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDoctor(), getPacient(), getContent(), getCreateDate(), getType(), isSeen);
    }
}
