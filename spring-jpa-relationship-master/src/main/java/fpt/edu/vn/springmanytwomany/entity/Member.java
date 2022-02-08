package fpt.edu.vn.springmanytwomany.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;

    @ManyToMany(mappedBy = "memberSet",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Set<ExchangeItem> exchangeItemSet;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Credential> credentialSet;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MemberInformation memberInformation;

    public Set<Credential> getCredentialSet() {
        return credentialSet;
    }

    public void setCredentialSet(Set<Credential> credentialSet) {
        this.credentialSet = credentialSet;
    }

    public MemberInformation getMemberInformation() {
        return memberInformation;
    }

    public void setMemberInformation(MemberInformation memberInformation) {
        this.memberInformation = memberInformation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<ExchangeItem> getExchangeItemSet() {
        return exchangeItemSet;
    }

    public void setExchangeItemSet(Set<ExchangeItem> exchangeItemSet) {
        this.exchangeItemSet = exchangeItemSet;
    }

    public void addExchangeItem(ExchangeItem exchangeItem) {
        if (this.exchangeItemSet == null) {
            this.exchangeItemSet = new HashSet<>();
        }
        this.exchangeItemSet.add(exchangeItem);
    }
}
