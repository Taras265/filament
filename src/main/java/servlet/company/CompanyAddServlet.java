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

@WebServlet("/companies/add")
public class CompanyAddServlet extends HttpServlet {

    private CompanyRepositoryInterface companyRepository;

    @Override
    public void init() {
        String dbPath = getServletContext().getRealPath("/WEB-INF/company_db.json");
        this.companyRepository = new CompanyJsonRepository(dbPath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/company/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String url = req.getParameter("url");
        Company company = new Company(name, url);

        companyRepository.addCompany(company);

        resp.sendRedirect(req.getContextPath() + "/companies");
    }
}