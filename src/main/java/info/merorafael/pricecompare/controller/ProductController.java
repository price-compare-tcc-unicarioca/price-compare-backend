package info.merorafael.pricecompare.controller;

import info.merorafael.pricecompare.data.request.NewProduct;
import info.merorafael.pricecompare.data.response.ResponseError;
import info.merorafael.pricecompare.entity.Product;
import info.merorafael.pricecompare.exception.ProductCannotDeletedException;
import info.merorafael.pricecompare.exception.ProductNotFoundException;
import info.merorafael.pricecompare.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
    protected final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<ResponseError> handleProductException(ProductNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseError("Product not found"));
    }

    @ExceptionHandler(ProductCannotDeletedException.class)
    ResponseEntity<ResponseError> handleProductException(ProductCannotDeletedException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseError(e.getMessage()));
    }

    @GetMapping
    @Operation(summary = "Get a page of products", security = @SecurityRequirement(name = "jwtAuth"))
    public ResponseEntity<Page<Product>> listProduct(@PageableDefault() Pageable pageable) {
        var products = repository.findAll(pageable);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(products);
    }

    @PostMapping
    @Operation(summary = "Create a new product", security = @SecurityRequirement(name = "jwtAuth"))
    protected ResponseEntity<Product> createProduct(@Valid @RequestBody NewProduct request) {
        var product = repository.save(request.toEntity());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(product);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific product", security = @SecurityRequirement(name = "jwtAuth"))
    protected ResponseEntity<Product> getProduct (@PathVariable("id") String productId)
            throws ProductNotFoundException {
        var product = repository.findById(productId).orElseThrow(ProductNotFoundException::new);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a specific product", security = @SecurityRequirement(name = "jwtAuth"))
    protected ResponseEntity<Object> deleteProduct(@PathVariable("id") String productId)
            throws ProductNotFoundException {
        var product = repository.findById(productId).orElseThrow(ProductNotFoundException::new);
        repository.delete(product);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
