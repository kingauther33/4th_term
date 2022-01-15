package fpt.edu.vn.springmanytwomany.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Credential {
    @Id
    private String token;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "memberId")
    private Member member;

    public Credential() {
        this.token = UUID.randomUUID().toString();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
