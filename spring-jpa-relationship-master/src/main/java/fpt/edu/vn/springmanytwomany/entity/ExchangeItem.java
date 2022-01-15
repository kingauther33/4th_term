package fpt.edu.vn.springmanytwomany.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ExchangeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "exchange_member",
            joinColumns = @JoinColumn(name = "exchange_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    private Set<Member> memberSet;


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

    public Set<Member> getMemberSet() {
        return memberSet;
    }

    public void setMemberSet(Set<Member> memberSet) {
        this.memberSet = memberSet;
    }

    public void addMemberSet(Member member) {
        if (this.memberSet == null) {
            this.memberSet = new HashSet<>();
        }
        this.memberSet.add(member);
    }
}
