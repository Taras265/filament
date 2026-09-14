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
import java.util.Optional;

@WebServlet("/companies/view")
public class CompanyViewServlet extends HttpServlet {

    private CompanyRepositoryInterface companyRepository;

    @Override
    public void init() {
        String dbPath = getServletContext().getRealPath("/WEB-INF/company_db.json");
        this.companyRepository = new CompanyJsonRepository(dbPath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || id.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/company");
            return;
        }

        Optional<Company> companyOpt = companyRepository.findByIdCompany(id);
        if (companyOpt.isPresent()) {
            req.setAttribute("company", companyOpt.get());
            req.getRequestDispatcher("/WEB-INF/jsp/company/view.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/companies");
        }
    }
}