package kz.AsylbekSpring.springbootAsyl.repository;

import kz.AsylbekSpring.springbootAsyl.db.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
