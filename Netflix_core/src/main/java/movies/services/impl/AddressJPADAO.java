package movies.services.impl;

import movies.datamodel.Address;
import movies.repositories.AddressRepository;
import movies.services.api.IAddressDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressJPADAO extends GenericJPADAO<Address> implements IAddressDAO{

    @Autowired
    private AddressRepository addressRepository;


    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }


    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    //Manually created select - outside the jpa scope
    public List<Address> select(Address address){
        return addressRepository.selectAddress(address.getCountry(),
                address.getArea(),address.getCity(),address.getStreet(),address.getNumber());
    }

    public void deleteAddressById(Long id) {
        addressRepository.deleteById(id);
    }







}
