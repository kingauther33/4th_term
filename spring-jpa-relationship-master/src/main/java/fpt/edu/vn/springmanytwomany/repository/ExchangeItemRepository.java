package fpt.edu.vn.springmanytwomany.repository;

import fpt.edu.vn.springmanytwomany.entity.ExchangeItem;
import fpt.edu.vn.springmanytwomany.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeItemRepository extends JpaRepository<ExchangeItem, Integer> {
}
