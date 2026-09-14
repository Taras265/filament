package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Filament;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FilamentJsonRepository implements FilamentRepositoryInterface {
    private final File storageFile;
    private final ObjectMapper objectMapper;

    public FilamentJsonRepository(String filePath) {
        this.storageFile = new File(filePath);
        System.out.println(storageFile.getAbsolutePath());
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
                objectMapper.writeValue(storageFile, new ArrayList<Filament>());
            } catch (IOException e) {
                throw new RuntimeException("Не удалось создать JSON хранилище", e);
            }
        }
    }

    @Override
    public List<Filament> findAllFilaments() {
        try {
            if (storageFile.length() == 0) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(storageFile, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Filament> findByIdFilament(String id) {
        return findAllFilaments().stream()
                .filter(f -> id.equals(f.getId()))
                .findFirst();
    }

    @Override
    public Filament addFilament(Filament filament) {
        List<Filament> filaments = findAllFilaments();

        filament.setId(UUID.randomUUID().toString());
        filaments.add(filament);

        saveAll(filaments);
        return filament;
    }

    @Override
    public Filament updateFilament(Filament filament) {
        List<Filament> filaments = findAllFilaments();

        boolean updated = false;
        for (int i = 0; i < filaments.size(); i++) {
            if (filaments.get(i).getId().equals(filament.getId())) {
                filaments.set(i, filament);
                updated = true;
                break;
            }
        }
        if (!updated) {
            filaments.add(filament);
        }

        saveAll(filaments);
        return filament;
    }

    @Override
    public boolean deleteByIdFilament(String id) {
        List<Filament> filaments = findAllFilaments();
        boolean removed = filaments.removeIf(f -> id.equals(f.getId()));
        if (removed) {
            saveAll(filaments);
        }
        return removed;
    }

    private void saveAll(List<Filament> filaments) {
        try {
            objectMapper.writeValue(storageFile, filaments);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в JSON БД", e);
        }
    }
}
