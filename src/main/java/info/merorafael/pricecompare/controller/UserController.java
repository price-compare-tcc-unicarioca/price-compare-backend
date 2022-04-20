package info.merorafael.pricecompare.controller;

import info.merorafael.pricecompare.data.response.AccessToken;
import info.merorafael.pricecompare.data.request.UserLogin;
import info.merorafael.pricecompare.data.request.UserSignup;
import info.merorafael.pricecompare.data.response.ResponseError;
import info.merorafael.pricecompare.entity.User;
import info.merorafael.pricecompare.exception.UserAlreadyExistsException;
import info.merorafael.pricecompare.repository.UserRepository;
import info.merorafael.pricecompare.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    protected final UserRepository repository;
    protected final AuthService authService;
    protected final AuthenticationManager authenticationManager;

    public UserController(
            UserRepository repository,
            AuthService authService,
            AuthenticationManager authenticationManager
    ) {
        this.repository = repository;
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    ResponseEntity<ResponseError> handleUserException(UserAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseError("User already exists"));
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@Valid @RequestBody UserSignup request) throws UserAlreadyExistsException {
        var optionalUser = repository.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        var user = repository.save(request.toUser());

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AccessToken> login(@Valid @RequestBody UserLogin request) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = (User) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.OK).body(
            authService.createToken(user)
        );
    }

    @GetMapping("/info")
    public ResponseEntity<User> info() {
        return ResponseEntity.status(HttpStatus.OK).body(authService.getUser());
    }
}
