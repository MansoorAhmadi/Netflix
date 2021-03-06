package movies.controllers;

import movies.datamodel.User;
import movies.internalServerError.InternalServerError;
import movies.exceptions.UserNotFoundException;
import movies.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    @Autowired
    UserRepository userRepository;

    // Create user
    @PostMapping
    public ResponseEntity<User>
    createUser(@RequestBody User user) {
        try {
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<User>
    updateUser(@PathVariable("id") Long id,
               @RequestBody User user) {

        Optional<User> userdata = userRepository.findById(id);
        if (userdata.isPresent()) {
            User _user = userdata.get();
            _user.setEmail(user.getEmail());
            _user.setFirstName(user.getFirstName());
            _user.setLastName(user.getLastName());
            return new ResponseEntity<>
                    (userRepository.save(_user), HttpStatus.OK);
        } else {
            throw new UserNotFoundException("Invalid User Id");
        }
    }

    // Get all Users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        try {
            List<User> users = new ArrayList<User>();
            userRepository.findAll().forEach(users::add);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }

    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User>
    getUserByID(@PathVariable("id") Long id) {

        Optional<User> userdata = userRepository.findById(id);
        if (userdata.isPresent()) {
            return new ResponseEntity<>
                    (userdata.get(), HttpStatus.OK);
        } else {
            throw new UserNotFoundException("Invalid User Id");
        }

    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<User>
    deleteUser(@PathVariable("id") Long id) {

        Optional<User> userdata = userRepository.findById(id);
        if (userdata.isPresent()) {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new UserNotFoundException("Invalid User Id");
        }
    }

}
