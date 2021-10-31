package by.lozovenko.xmlparser.entity;

import java.math.BigDecimal;

public class CallPrice {
    private BigDecimal insideNetworkCallPrice;
    private BigDecimal outsideNetworkCallPrice;
    private BigDecimal stationaryPhoneCallPrice;

    public CallPrice(BigDecimal insideNetworkCallPrice, BigDecimal outsideNetworkCallPrice,
                     BigDecimal stationaryPhoneCallPrice) {
        this.insideNetworkCallPrice = insideNetworkCallPrice;
        this.outsideNetworkCallPrice = outsideNetworkCallPrice;
        this.stationaryPhoneCallPrice = stationaryPhoneCallPrice;
    }

    public BigDecimal getInsideNetworkCallPrice() {
        return insideNetworkCallPrice;
    }

    public void setInsideNetworkCallPrice(BigDecimal insideNetworkCallPrice) {
        this.insideNetworkCallPrice = insideNetworkCallPrice;
    }

    public BigDecimal getOutsideNetworkCallPrice() {
        return outsideNetworkCallPrice;
    }

    public void setOutsideNetworkCallPrice(BigDecimal outsideNetworkCallPrice) {
        this.outsideNetworkCallPrice = outsideNetworkCallPrice;
    }

    public BigDecimal getStationaryPhoneCallPrice() {
        return stationaryPhoneCallPrice;
    }

    public void setStationaryPhoneCallPrice(BigDecimal stationaryPhoneCallPrice) {
        this.stationaryPhoneCallPrice = stationaryPhoneCallPrice;
    }
}
