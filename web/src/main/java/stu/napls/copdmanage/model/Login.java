package stu.napls.copdmanage.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Yepeng Ding
 */
@Entity
@Table(name = "login")
@Data
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "createTime")
    private Date createTime;

    @Column(name = "updateTime")
    private Date updateTime;

    @OneToOne
    @JoinColumn(name="userId")
    private User user;

}
