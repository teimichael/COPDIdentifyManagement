package stu.napls.copdmanage.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Yepeng Ding
 */
@Entity
@Table(name = "admin")
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "privilege")
    private String privilege;

    @ManyToOne
    @JoinColumn(name="loginId")
    private Login login;
}
