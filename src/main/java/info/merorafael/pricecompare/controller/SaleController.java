package info.merorafael.pricecompare.controller;

import info.merorafael.pricecompare.data.request.Base64File;
import info.merorafael.pricecompare.data.request.SearchSaleNear;
import info.merorafael.pricecompare.entity.Sale;
import info.merorafael.pricecompare.exception.CompanyNotFoundException;
import info.merorafael.pricecompare.exception.ProductNotFoundException;
import info.merorafael.pricecompare.repository.ProductRepository;
import info.merorafael.pricecompare.repository.SaleRepository;
import info.merorafael.pricecompare.service.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {
    protected final ProductRepository productRepository;
    protected final SaleRepository repository;
    protected final SaleService service;

    public SaleController(
            ProductRepository productRepository,
            SaleRepository repository,
            SaleService service
    ) {
        this.productRepository = productRepository;
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/search")
    @Operation(summary = "Get a page of near product sale", security = @SecurityRequirement(name = "jwtAuth"))
    public ResponseEntity<Page<Sale>> searchNear(@PageableDefault() Pageable pageable, @Valid SearchSaleNear request)
            throws ProductNotFoundException {
        var sales = service.searchNear(request, pageable);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sales);
    }

    @PostMapping("/import")
    @Operation(summary = "Import a XLSX file with sale list", security = @SecurityRequirement(name = "jwtAuth"))
    public ResponseEntity<List<Sale>> importList(@Valid @RequestBody Base64File base64File)
            throws IOException, CompanyNotFoundException {
        var sales = service.importSheet(base64File);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sales);
    }
}
