package servlet.filament;

import model.Company;
import model.Filament;
import model.FilamentType;
import repository.CompanyJsonRepository;
import repository.CompanyRepositoryInterface;
import repository.FilamentJsonRepository;
import repository.FilamentRepositoryInterface;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class FilamentAddServlet extends HttpServlet {

    private FilamentRepositoryInterface filamentRepository;
    private CompanyRepositoryInterface companyRepository;

    @Override
    public void init() {
        String filamentsDbPath = getServletContext().getRealPath("/WEB-INF/filament_db.json");
        String companiesDbPath = getServletContext().getRealPath("/WEB-INF/company_db.json");

        this.filamentRepository = new FilamentJsonRepository(filamentsDbPath);
        this.companyRepository = new CompanyJsonRepository(companiesDbPath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("companies", companyRepository.findAllCompanies());
        req.getRequestDispatcher("/WEB-INF/jsp/filament/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        String color = req.getParameter("color");
        FilamentType filamentType = FilamentType.valueOf(req.getParameter("filamentType"));
        String companyId = req.getParameter("companyId");
        int maxWeight = Integer.parseInt(req.getParameter("maxWeight"));
        int weight = Integer.parseInt(req.getParameter("weight"));
        int maxLength = Integer.parseInt(req.getParameter("maxLength"));
        int length = Integer.parseInt(req.getParameter("length"));
        String url = req.getParameter("url");

        Company company = companyRepository.findByIdCompany(companyId).orElse(null);

        Filament filament = new Filament(color, maxWeight, weight,
                maxLength, length, filamentType, company, url);
        filamentRepository.addFilament(filament);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}