package movies.services;

import movies.datamodel.Address;
import movies.datamodel.Movie;
import movies.exceptions.MoviesNotFoundException;
import movies.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Movie> getAddressById(Long id) {
        List<Movie> movieList = addressRepository.findById(id);
        if (!movieList.isEmpty()) {
            return movieList.get();
        } else {
            throw new MoviesNotFoundException("Movie with Id : " + id + " Not Found");
        }
    }

    public void deleteAddressById(Long id) {
        addressRepository.delete(getAllAddresses(id));
    }







}
