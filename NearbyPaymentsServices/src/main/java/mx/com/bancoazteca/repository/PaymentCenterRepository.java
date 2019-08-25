package mx.com.bancoazteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.bancoazteca.entity.PaymentCenter;

@Repository
public interface PaymentCenterRepository extends JpaRepository<PaymentCenter, Integer> {

}
