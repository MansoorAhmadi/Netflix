package movies.services;

import movies.datamodel.Address;
import movies.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

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



//    public void deleteAddressById(Long id) {
//        addressRepository.delete(getAllAddresses(id));
//    }







}
