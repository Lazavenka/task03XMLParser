package by.lozovenko.xmlparser.entity;

import java.math.BigDecimal;

public class TariffParameter {
    private int favouriteNumbers;
    private BigDecimal connectionPayment;
    private Billing billing;

    public TariffParameter(int favouriteNumbers, BigDecimal connectionPayment, Billing billing) {
        this.favouriteNumbers = favouriteNumbers;
        this.connectionPayment = connectionPayment;
        this.billing = billing;
    }

    public int getFavouriteNumbers() {
        return favouriteNumbers;
    }

    public void setFavouriteNumbers(int favouriteNumbers) {
        this.favouriteNumbers = favouriteNumbers;
    }

    public BigDecimal getConnectionPayment() {
        return connectionPayment;
    }

    public void setConnectionPayment(BigDecimal connectionPayment) {
        this.connectionPayment = connectionPayment;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }
}
