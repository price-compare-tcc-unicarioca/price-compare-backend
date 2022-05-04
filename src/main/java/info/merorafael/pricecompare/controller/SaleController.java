package info.merorafael.pricecompare.controller;

import info.merorafael.pricecompare.data.request.Base64File;
import info.merorafael.pricecompare.data.request.RequestSaleNear;
import info.merorafael.pricecompare.entity.Sale;
import info.merorafael.pricecompare.exception.CompanyNotFoundException;
import info.merorafael.pricecompare.repository.SaleRepository;
import info.merorafael.pricecompare.service.SaleService;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {
    protected final SaleRepository repository;
    protected final SaleService service;

    public SaleController(SaleRepository repository, SaleService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Sale>> searchNear(@PageableDefault() Pageable pageable, RequestSaleNear request) {
        var salePage = repository.findByProductEanAndCompanyPointNear(
                request.getEan(),
                request.toGeoJsonPoint(),
                pageable
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(salePage);
    }

    @PostMapping("/import")
    public ResponseEntity<List<Sale>> importList(@Valid @RequestBody Base64File base64File)
            throws IOException, CompanyNotFoundException {
        var sales = service.importSheet(base64File);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sales);
    }
}
