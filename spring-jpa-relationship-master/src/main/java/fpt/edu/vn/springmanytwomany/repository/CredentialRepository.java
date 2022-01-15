package fpt.edu.vn.springmanytwomany.repository;

import fpt.edu.vn.springmanytwomany.entity.Credential;
import fpt.edu.vn.springmanytwomany.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credential, String> {

}
