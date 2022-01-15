package fpt.edu.vn.springmanytwomany.controller;

import fpt.edu.vn.springmanytwomany.entity.Credential;
import fpt.edu.vn.springmanytwomany.entity.ExchangeItem;
import fpt.edu.vn.springmanytwomany.entity.Member;
import fpt.edu.vn.springmanytwomany.entity.MemberInformation;
import fpt.edu.vn.springmanytwomany.repository.CredentialRepository;
import fpt.edu.vn.springmanytwomany.repository.ExchangeItemRepository;
import fpt.edu.vn.springmanytwomany.repository.MemberInformationRepository;
import fpt.edu.vn.springmanytwomany.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberInformationRepository memberInformationRepository;

    @Autowired
    CredentialRepository credentialRepository;

    @Autowired
    ExchangeItemRepository exchangeItemRepository;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloWorld() {
        return "okie.";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hollo")
    public String demoOneOne() {
        Member member = memberRepository.findAll().get(0);
        member.setUsername("xuan hung");

        MemberInformation memberInformation = new MemberInformation();
        memberInformation.setAddress("Hanoi");
        memberInformation.setMember(member);
        memberInformationRepository.save(memberInformation);
//        memberRepository.save(member);
        return "okie.";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hollo-des")
    public String demoDeleteOneOne() {
        memberRepository.delete(memberRepository.findAll().get(0));
//        MemberInformation memberInformation = memberInformationRepository.findAll().get(0);
//        memberInformationRepository.delete(memberInformation);
        return "okie.";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hallo")
    public String demoOneMany() {
        Member member = new Member();
        member.setUsername("xuantung");
        Credential credential = new Credential();
        credential.setMember(member);
        credentialRepository.save(credential);
        return "okie.";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hallo-destroy")
    public String demoDeleteOneMany() {
        Credential credential = credentialRepository.findAll().get(0);
        credentialRepository.delete(credential);
        return "okie.";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String demoManyToMany() {
//        Member member = new Member();
//        member.setUsername("xuanhung");

//        Member member2 = new Member();
//        member2.setUsername("nhung");
        Member member = memberRepository.findAll().get(0);
        member.setUsername("Hello JPA");
        ExchangeItem exchangeItem = new ExchangeItem();
        exchangeItem.setName("exchange 1");

        exchangeItem.addMemberSet(member);
//        exchangeItem.addMemberSet(member2);
        exchangeItemRepository.save(exchangeItem);
        return "okie.";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/destroy")
    public String destroy() {
        ExchangeItem exchangeItem = exchangeItemRepository.findById(1).orElse(null);
        exchangeItemRepository.delete(exchangeItem);
        return "okie";
    }
}
