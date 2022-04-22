package info.merorafael.pricecompare.exception;

public class ProductCannotDeletedException extends Exception {
    public ProductCannotDeletedException() {
        super();
    }

    public ProductCannotDeletedException(String message) {
        super(message);
    }
}
