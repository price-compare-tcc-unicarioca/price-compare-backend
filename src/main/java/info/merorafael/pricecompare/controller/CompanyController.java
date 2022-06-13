package info.merorafael.pricecompare.controller;

import info.merorafael.pricecompare.data.request.EditCompany;
import info.merorafael.pricecompare.data.request.NewCompany;
import info.merorafael.pricecompare.data.response.ResponseError;
import info.merorafael.pricecompare.entity.Company;
import info.merorafael.pricecompare.exception.CompanyAlreadyExistsException;
import info.merorafael.pricecompare.exception.CompanyNotFoundException;
import info.merorafael.pricecompare.repository.CompanyRepository;
import info.merorafael.pricecompare.service.AuthService;
import info.merorafael.pricecompare.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    protected final AuthService authService;

    protected final CompanyRepository repository;
    protected final CompanyService service;

    public CompanyController(AuthService authService, CompanyRepository repository, CompanyService service) {
        this.authService = authService;
        this.repository = repository;
        this.service = service;
    }

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    ResponseEntity<ResponseError> handleCompanyException(CompanyAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseError("Company already exists"));
    }

    @GetMapping
    @Operation(summary = "List companies", security = @SecurityRequirement(name = "jwtAuth"))
    protected ResponseEntity<List<Company>> list() {
        var companies = repository.findByUser(new ObjectId(authService.getUser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(companies);
    }

    @PostMapping
    @Operation(summary = "Create a new company", security = @SecurityRequirement(name = "jwtAuth"))
    protected ResponseEntity<Company> createCompany(@Valid @RequestBody NewCompany request)
            throws CompanyAlreadyExistsException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createCompany(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit a company", security = @SecurityRequirement(name = "jwtAuth"))
    protected ResponseEntity<Company> editCompany(
            @Valid @RequestBody EditCompany request,
            @PathVariable("id") String companyId
    ) throws CompanyNotFoundException {
        var company = repository.findById(companyId)
                .orElseThrow(CompanyNotFoundException::new);

        company.setPoint(request.getPoint().toGeoJsonPoint());

        repository.save(company);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(company);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a company", security = @SecurityRequirement(name = "jwtAuth"))
    public ResponseEntity<Object> deleteCompany(@PathVariable("id") String companyId) throws CompanyNotFoundException {
        service.deleteCompany(companyId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
