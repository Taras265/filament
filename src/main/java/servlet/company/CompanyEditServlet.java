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

@WebServlet("/companies/edit")
public class CompanyEditServlet extends HttpServlet {

    private CompanyRepositoryInterface companyRepository;

    @Override
    public void init() {
        String dbPath = getServletContext().getRealPath("/WEB-INF/company_db.json");
        this.companyRepository = new CompanyJsonRepository(dbPath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Optional<Company> companyOpt = companyRepository.findByIdCompany(id);

        if (companyOpt.isPresent()) {
            req.setAttribute("company", companyOpt.get());
            req.getRequestDispatcher("/WEB-INF/jsp/company/edit.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/companies");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String url = req.getParameter("url");

        Company company = new Company(name, url);
        company.setId(id);

        companyRepository.updateCompany(company);

        resp.sendRedirect(req.getContextPath() + "/companies");
    }
}