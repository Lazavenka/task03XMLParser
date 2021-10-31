package by.lozovenko.xmlparser.builder;

import by.lozovenko.xmlparser.entity.AbstractTariff;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractTariffBuilder {
    private Set<AbstractTariff> tariffs;

    public AbstractTariffBuilder(){
        tariffs = new HashSet<>();
    }

    public Set<AbstractTariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(Set<AbstractTariff> tariffs) {
        this.tariffs = tariffs;
    }
    public abstract void buildTariffs(String uri);
}
