package info.merorafael.pricecompare.service;

import info.merorafael.pricecompare.data.request.Base64File;
import info.merorafael.pricecompare.entity.Product;
import info.merorafael.pricecompare.entity.Sale;
import info.merorafael.pricecompare.exception.CompanyNotFoundException;
import info.merorafael.pricecompare.repository.CompanyRepository;
import info.merorafael.pricecompare.repository.ProductRepository;
import info.merorafael.pricecompare.repository.SaleRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SaleService {
    protected final CompanyRepository companyRepository;
    protected final ProductRepository productRepository;
    protected final SaleRepository saleRepository;

    public SaleService(
            CompanyRepository companyRepository,
            ProductRepository productRepository,
            SaleRepository saleRepository
    ) {
        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    public List<Sale> importSheet(Base64File file) throws IOException, CompanyNotFoundException {
        var sales = new ArrayList<Sale>();

        try (var workbook = WorkbookFactory.create(file.getInputStream())) {
            var sheet = workbook.getSheetAt(0);

            var companyDocument = sheet.getRow(1).getCell(1).getStringCellValue().trim();
            var company = companyRepository
                    .findByDocument(companyDocument)
                    .orElseThrow(CompanyNotFoundException::new);

            saleRepository.deleteAllByCompanyId(company.getId());

            var rows = sheet.getLastRowNum();
            for (int i = 5; i < rows; i++) {
                var ean = sheet.getRow(i).getCell(0).getStringCellValue().trim();
                var productName = sheet.getRow(i).getCell(1).getStringCellValue().trim();
                var price = sheet.getRow(i).getCell(2).getNumericCellValue();
                if (!ean.isEmpty() && price > 0) {
                    var optionalProduct = productRepository.findByEan(ean);
                    var product = optionalProduct.isEmpty()
                            ? productRepository.save(new Product().setName(productName).setEan(ean))
                            : optionalProduct.get();

                    sales.add(saleRepository.save(
                            new Sale()
                                    .setProduct(product)
                                    .setCompany(company)
                                    .setPrice(BigDecimal.valueOf(price))
                    ));
                }
            }
        }

        return sales;
    }
}
