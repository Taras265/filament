package servlet.company;

import model.Company;
import repository.CompanyJsonRepository;
import repository.CompanyRepositoryInterface;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/companies")
public class CompanyListServlet extends HttpServlet {

    private CompanyRepositoryInterface companyRepository;

    @Override
    public void init() {
        String dbPath = getServletContext().getRealPath("/WEB-INF/company_db.json");
        this.companyRepository = new CompanyJsonRepository(dbPath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Company> companies = companyRepository.findAllCompanies();
        req.setAttribute("companies", companies);
        req.getRequestDispatcher("/WEB-INF/jsp/company/main.jsp").forward(req, resp);
    }
}