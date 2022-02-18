package movies.services.api;

import movies.datamodel.Address;

import java.util.List;

public interface IAddressDAO extends IDAO<Address> {
    //nothing - no other method.
    Address saveAddress(Address address);
    List<Address> getAllAddresses();
    List<Address> select(Address address);
    void deleteAddressById(Long id);
}
