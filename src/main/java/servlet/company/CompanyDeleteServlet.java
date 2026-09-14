package servlet.company;

import model.Company;
import model.Filament;
import repository.CompanyJsonRepository;
import repository.CompanyRepositoryInterface;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.FilamentJsonRepository;
import repository.FilamentRepositoryInterface;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/companies/delete")
public class CompanyDeleteServlet extends HttpServlet {
    private FilamentRepositoryInterface filamentRepository;
    private CompanyRepositoryInterface companyRepository;

    @Override
    public void init() {
        String dbPath = getServletContext().getRealPath("/WEB-INF/company_db.json");
        this.companyRepository = new CompanyJsonRepository(dbPath);

        String fDbPath = getServletContext().getRealPath("/WEB-INF/filament_db.json");
        this.filamentRepository = new FilamentJsonRepository(fDbPath);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");

        if (id != null && !id.isBlank()) {
            Optional<Company> company = companyRepository.findByIdCompany(id);
            if (company.isPresent() && !cascadeCheck(company.get())) {
                companyRepository.deleteByIdCompany(id);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/companies");
    }

    private boolean cascadeCheck(Company company) {
        List<Filament> filaments = filamentRepository.findAllFilaments();

        return filaments.stream()
                .filter(f -> f.getCompany() != null && f.getCompany().getId() != null)
                .anyMatch(f -> f.getCompany().getId().equals(company.getId()));
    }
}