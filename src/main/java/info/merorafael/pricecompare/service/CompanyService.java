package info.merorafael.pricecompare.service;

import info.merorafael.pricecompare.data.enums.CompanyRole;
import info.merorafael.pricecompare.data.request.NewCompany;
import info.merorafael.pricecompare.entity.Company;
import info.merorafael.pricecompare.entity.CompanyUser;
import info.merorafael.pricecompare.exception.CompanyAlreadyExistsException;
import info.merorafael.pricecompare.exception.CompanyNotFoundException;
import info.merorafael.pricecompare.repository.CompanyRepository;
import info.merorafael.pricecompare.repository.SaleRepository;
import info.merorafael.pricecompare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    protected final CompanyRepository companyRepository;
    protected final SaleRepository saleRepository;
    protected final AuthService authService;

    public CompanyService(
            CompanyRepository companyRepository,
            SaleRepository saleRepository,
            AuthService authService
    ) {
        this.companyRepository = companyRepository;
        this.saleRepository = saleRepository;
        this.authService = authService;
    }

    public Company createCompany(NewCompany newCompany) throws CompanyAlreadyExistsException {
        var optionalCompany = companyRepository.findByDocument(newCompany.getDocument());
        if (optionalCompany.isPresent()) {
            throw new CompanyAlreadyExistsException();
        }

        var company = newCompany.toCompany();
        var companyUser = new CompanyUser(authService.getUser(), List.of(CompanyRole.ROLE_ADMIN));
        company.setUsers(List.of(companyUser));

        return companyRepository.save(company);
    }

    public void deleteCompany(String companyId) throws CompanyNotFoundException {
        var company = companyRepository.findById(companyId).orElseThrow(CompanyNotFoundException::new);
        if (!company.hasPermissionToChange(authService.getUser())) {
            throw new CompanyNotFoundException();
        }

        saleRepository.deleteAllByCompanyId(company.getId());
        companyRepository.delete(company);
    }
}
