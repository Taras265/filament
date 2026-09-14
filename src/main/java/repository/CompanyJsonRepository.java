package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CompanyJsonRepository implements CompanyRepositoryInterface {
    private final File storageFile;
    private final ObjectMapper objectMapper;

    public CompanyJsonRepository(String filePath) {
        this.storageFile = new File(filePath);
        this.objectMapper = new ObjectMapper();

        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        initStorage();
    }

    private synchronized void initStorage() {
        if (!storageFile.exists()) {
            try {
                if (storageFile.getParentFile() != null) {
                    storageFile.getParentFile().mkdirs();
                }
                storageFile.createNewFile();
                objectMapper.writeValue(storageFile, new ArrayList<Company>());
            } catch (IOException e) {
                throw new RuntimeException("Не удалось создать JSON хранилище для компаний", e);
            }
        }
    }

    @Override
    public List<Company> findAllCompanies() {
        try {
            if (storageFile.length() == 0) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(storageFile, new TypeReference<List<Company>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Company> findByIdCompany(String id) {
        return findAllCompanies().stream()
                .filter(c -> id.equals(c.getId()))
                .findFirst();
    }

    @Override
    public Company addCompany(Company company) {
        List<Company> companies = findAllCompanies();

        company.setId(UUID.randomUUID().toString());
        companies.add(company);

        saveAll(companies);
        return company;
    }

    @Override
    public Company updateCompany(Company company) {
        List<Company> companies = findAllCompanies();

        boolean updated = false;
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getId().equals(company.getId())) {
                companies.set(i, company);
                updated = true;
                break;
            }
        }
        if (!updated) {
            companies.add(company);
        }

        saveAll(companies);
        return company;
    }

    @Override
    public boolean deleteByIdCompany(String id) {
        List<Company> companies = findAllCompanies();
        boolean removed = companies.removeIf(c -> id.equals(c.getId()));
        if (removed) {
            saveAll(companies);
        }
        return removed;
    }

    private void saveAll(List<Company> companies) {
        try {
            objectMapper.writeValue(storageFile, companies);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи компаний в JSON БД", e);
        }
    }
}