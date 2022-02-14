package movies.repositories;

import movies.datamodel.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    //outside the jpa scope
    List<Address> selectAddress(String country, String area, String city, String street, String number);
}
