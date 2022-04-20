package info.merorafael.pricecompare.controller;

import info.merorafael.pricecompare.data.request.EditCompany;
import info.merorafael.pricecompare.data.request.NewCompany;
import info.merorafael.pricecompare.data.response.ResponseError;
import info.merorafael.pricecompare.entity.Company;
import info.merorafael.pricecompare.exception.CompanyAlreadyExistsException;
import info.merorafael.pricecompare.exception.CompanyNotFoundException;
import info.merorafael.pricecompare.repository.CompanyRepository;
import info.merorafael.pricecompare.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
    protected final CompanyRepository repository;
    protected final CompanyService service;

    public CompanyController(CompanyRepository repository, CompanyService service) {
        this.repository = repository;
        this.service = service;
    }

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    ResponseEntity<ResponseError> handleUserException(CompanyAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseError("Company already exists"));
    }

    @GetMapping
    protected ResponseEntity<Page<Company>> listCompany(@PageableDefault() Pageable pageable) {
        var companies = repository.findAll(pageable);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companies);
    }

    @PostMapping
    protected ResponseEntity<Company> createCompany(@Valid @RequestBody NewCompany request)
            throws CompanyAlreadyExistsException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createCompany(request));
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<Object> deleteCompany(@PathVariable("id") String companyId) throws CompanyNotFoundException {
        var company = repository.findById(companyId)
                .orElseThrow(CompanyNotFoundException::new);

        repository.delete(company);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
