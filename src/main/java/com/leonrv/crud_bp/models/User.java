package com.leonrv.crud_bp.models;
import java.io.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class User implements Serializable{
    static final long serialVersionUID = 3L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    private String gender;
    private Boolean status;

    // @Column(columnDefinition = "BLOB")
    // private byte[] image;
    @Column(length = 500)
    private String urlImage;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Email> emails;

}
