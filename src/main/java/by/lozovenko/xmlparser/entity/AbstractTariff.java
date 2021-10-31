package by.lozovenko.xmlparser.entity;

import java.math.BigDecimal;

public class AbstractTariff {
    private String tariffId;
    private String name;
    private MobileOperator operatorName;
    private BigDecimal payroll;
    private TariffParameter parameter;
    public AbstractTariff(){}
    public AbstractTariff(String tariffId, String name, MobileOperator operatorName,
                          BigDecimal payroll, TariffParameter parameter) {
        this.tariffId = tariffId;
        this.name = name;
        this.operatorName = operatorName;
        this.payroll = payroll;
        this.parameter = parameter;
    }

    public String getTariffId() {
        return tariffId;
    }

    public void setTariffId(String tariffId) {
        this.tariffId = tariffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MobileOperator getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(MobileOperator operatorName) {
        this.operatorName = operatorName;
    }

    public BigDecimal getPayroll() {
        return payroll;
    }

    public void setPayroll(BigDecimal payroll) {
        this.payroll = payroll;
    }

    public TariffParameter getParameter() {
        return parameter;
    }

    public void setParameter(TariffParameter parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nTariff - ").append(getClass().getSimpleName());
        sb.append("\n\ttariffId='").append(tariffId).append('\'');
        sb.append("\n\tname='").append(name).append('\'');
        sb.append("\n\toperatorName=").append(operatorName);
        sb.append("\n\tpayroll=").append(payroll);
        sb.append(parameter);
        return sb.toString();
    }
}
