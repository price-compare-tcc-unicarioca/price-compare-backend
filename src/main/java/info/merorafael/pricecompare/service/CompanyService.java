package info.merorafael.pricecompare.service;

import info.merorafael.pricecompare.data.request.NewCompany;
import info.merorafael.pricecompare.entity.Company;
import info.merorafael.pricecompare.exception.CompanyAlreadyExistsException;
import info.merorafael.pricecompare.repository.CompanyRepository;
import info.merorafael.pricecompare.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    protected final CompanyRepository companyRepository;
    protected final UserRepository userRepository;
    protected final AuthService authService;

    public CompanyService(
            CompanyRepository companyRepository,
            UserRepository userRepository,
            AuthService authService
    ) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public Company createCompany(NewCompany newCompany) throws CompanyAlreadyExistsException {
        var optionalCompany = companyRepository.findByDocument(newCompany.getDocument());
        if (optionalCompany.isPresent()) {
            throw new CompanyAlreadyExistsException();
        }

        var company = companyRepository.save(newCompany.toCompany());

        var currentUser = authService.getUser();
        currentUser.getCompanies().add(company);
        userRepository.save(currentUser);

        return company;
    }
}
