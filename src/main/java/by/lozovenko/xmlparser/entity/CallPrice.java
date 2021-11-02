package by.lozovenko.xmlparser.entity;

import java.math.BigDecimal;

public class CallPrice {
    private BigDecimal insideNetworkCallPrice;
    private BigDecimal outsideNetworkCallPrice;
    private BigDecimal stationaryPhoneCallPrice;

    public CallPrice() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CallPrice callPrice = (CallPrice) o;

        if (insideNetworkCallPrice != null ? !insideNetworkCallPrice.equals(callPrice.insideNetworkCallPrice) : callPrice.insideNetworkCallPrice != null) {
            return false;
        }
        if (outsideNetworkCallPrice != null ? !outsideNetworkCallPrice.equals(callPrice.outsideNetworkCallPrice) : callPrice.outsideNetworkCallPrice != null) {
            return false;
        }
        return stationaryPhoneCallPrice != null ? stationaryPhoneCallPrice.equals(callPrice.stationaryPhoneCallPrice) : callPrice.stationaryPhoneCallPrice == null;
    }

    @Override
    public int hashCode() {
        int result = insideNetworkCallPrice != null ? insideNetworkCallPrice.hashCode() : 0;
        result = 31 * result + (outsideNetworkCallPrice != null ? outsideNetworkCallPrice.hashCode() : 0);
        result = 31 * result + (stationaryPhoneCallPrice != null ? stationaryPhoneCallPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n\tCallPrice:");
        sb.append("\n\t\tinsideNetworkCallPrice=").append(insideNetworkCallPrice);
        sb.append("\n\t\toutsideNetworkCallPrice=").append(outsideNetworkCallPrice);
        sb.append("\n\t\tstationaryPhoneCallPrice=").append(stationaryPhoneCallPrice);
        return sb.toString();
    }
}
