package servlet.filament;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Filament;
import model.FilamentType;
import repository.CompanyJsonRepository;
import repository.CompanyRepositoryInterface;
import repository.FilamentJsonRepository;
import repository.FilamentRepositoryInterface;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/")
public class FilamentListServlet extends HttpServlet {
    private FilamentRepositoryInterface filamentRepository;
    private CompanyRepositoryInterface companyRepository;

    @Override
    public void init() {
        String dbPath = getServletContext().getRealPath("/WEB-INF/filament_db.json");
        this.filamentRepository = new FilamentJsonRepository(dbPath);
        String сDbPath = getServletContext().getRealPath("/WEB-INF/company_db.json");
        this.companyRepository = new CompanyJsonRepository(сDbPath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Filament> filteredList = filterFilaments(req);

        req.setAttribute("filaments", filteredList);
        req.setAttribute("companies", companyRepository.findAllCompanies());
        req.setAttribute("filamentTypes", FilamentType.values());
        System.out.println(companyRepository.findAllCompanies());
        req.getRequestDispatcher("/WEB-INF/jsp/filament/main.jsp").forward(req, resp);
    }

    private List<Filament> filterFilaments(HttpServletRequest req) {
        List<Filament> filaments = filamentRepository.findAllFilaments();

        if (filaments == null || filaments.isEmpty()) {
            return filaments;
        }

        // Отримуємо значення з форми пошуку
        String color = req.getParameter("color");
        String filamentType = req.getParameter("filamentType");
        String companyIdStr = req.getParameter("companyId");

        Double minWeight = parseDouble(req.getParameter("minWeight"));
        Double maxWeight = parseDouble(req.getParameter("maxWeight"));

        Double minLength = parseDouble(req.getParameter("minLength"));
        Double maxLength = parseDouble(req.getParameter("maxLength"));

        return filaments.stream()
                // Фільтр за кольором (пошук за частковим збігом без урахування регістру)
                .filter(f -> color == null || color.isBlank() ||
                        (f.getColor() != null && f.getColor().toLowerCase().contains(color.trim().toLowerCase())))

                // Фільтр за типом філаменту
                .filter(f -> filamentType == null || filamentType.isBlank() ||
                        (f.getFilamentType() != null && f.getFilamentType().name().equalsIgnoreCase(filamentType)))

                // Фільтр за компанією
                .filter(f -> {
                    if (companyIdStr == null || companyIdStr.isBlank()) return true;
                    if (f.getCompany() == null || f.getCompany().getId() == null) return false;
                    return f.getCompany().getId().toString().equals(companyIdStr);
                })

                // Фільтри за вагою
                .filter(f -> minWeight == null || f.getWeight() >= minWeight)
                .filter(f -> maxWeight == null || f.getWeight() <= maxWeight)

                // Фільтри за довжиною
                .filter(f -> minLength == null || f.getLength() >= minLength)
                .filter(f -> maxLength == null || f.getLength() <= maxLength)

                .collect(Collectors.toList());
    }

    private Double parseDouble(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return null; // Якщо користувач ввів некоректне число, ігноруємо цей фільтр
        }
    }
}
