package repository;

import model.Filament;

import java.util.List;
import java.util.Optional;

public interface FilamentRepositoryInterface {
    List<Filament> findAllFilaments();
    Optional<Filament> findByIdFilament(String id);
    Filament addFilament(Filament filament);
    Filament updateFilament(Filament filament);
    boolean deleteByIdFilament(String id);
}
