package by.lozovenko.xmlparser.entity;

import java.math.BigDecimal;

public class AbstractTariff {
    private String tariffId;
    private String name;
    private MobileOperator operatorName;
    private BigDecimal payroll;
    private TariffParameter parameter;

    public AbstractTariff() {
    }

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractTariff that = (AbstractTariff) o;

        if (tariffId != null ? !tariffId.equals(that.tariffId) : that.tariffId != null) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (operatorName != that.operatorName) {
            return false;
        }
        if (payroll != null ? !payroll.equals(that.payroll) : that.payroll != null) {
            return false;
        }
        return parameter != null ? parameter.equals(that.parameter) : that.parameter == null;
    }

    @Override
    public int hashCode() {
        int result = tariffId != null ? tariffId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (operatorName != null ? operatorName.hashCode() : 0);
        result = 31 * result + (payroll != null ? payroll.hashCode() : 0);
        result = 31 * result + (parameter != null ? parameter.hashCode() : 0);
        return result;
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
