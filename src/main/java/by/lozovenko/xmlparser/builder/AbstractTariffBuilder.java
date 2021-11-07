package by.lozovenko.xmlparser.builder;

import by.lozovenko.xmlparser.entity.AbstractTariff;
import by.lozovenko.xmlparser.exception.ProjectException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractTariffBuilder {
    private Set<AbstractTariff> tariffs;

    protected AbstractTariffBuilder() {
        tariffs = new HashSet<>();
    }

    public Set<AbstractTariff> getTariffs() {
        return tariffs;
    }

    public AbstractTariff getElement(int id) {
        return List.copyOf(tariffs).get(id);
    }

    public void setTariffs(Set<AbstractTariff> tariffs) {
        this.tariffs = tariffs;
    }

    public abstract void buildTariffs(String filepath) throws ProjectException;
}
