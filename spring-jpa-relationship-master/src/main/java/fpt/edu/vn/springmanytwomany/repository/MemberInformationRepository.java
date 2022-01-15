package fpt.edu.vn.springmanytwomany.repository;

import fpt.edu.vn.springmanytwomany.entity.Member;
import fpt.edu.vn.springmanytwomany.entity.MemberInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInformationRepository extends JpaRepository<MemberInformation, Integer> {
}
