package stu.napls.copdmanage.model;


import lombok.Data;

import javax.persistence.*;

/**
 * @author Yepeng Ding
 */
@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

}
