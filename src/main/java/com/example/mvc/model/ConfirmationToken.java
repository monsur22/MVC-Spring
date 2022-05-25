package com.example.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "confirmationToken")
@EntityListeners(AuditingEntityListener.class)
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "id_user")
    private com.example.mvc.model.User user;

    public ConfirmationToken(com.example.mvc.model.User user) {
        this.user = user;
        this.token =token;
        createdDate = new Date();
    }

    public ConfirmationToken() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public com.example.mvc.model.User getUser() {
        return user;
    }

    public void setUser(com.example.mvc.model.User user) {
        this.user = user;
    }

}