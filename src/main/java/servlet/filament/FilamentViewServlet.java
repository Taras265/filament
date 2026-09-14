package servlet.filament;

import model.Filament;
import repository.FilamentJsonRepository;
import repository.FilamentRepositoryInterface;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/view")
public class FilamentViewServlet extends HttpServlet {

    private FilamentRepositoryInterface filamentRepository;

    @Override
    public void init() {
        String dbPath = getServletContext().getRealPath("/WEB-INF/filament_db.json");
        this.filamentRepository = new FilamentJsonRepository(dbPath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || id.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/filament");
            return;
        }

        Optional<Filament> filamentOpt = filamentRepository.findByIdFilament(id);
        if (filamentOpt.isPresent()) {
            req.setAttribute("filament", filamentOpt.get());
            req.getRequestDispatcher("/WEB-INF/jsp/filament/view.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}