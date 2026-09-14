package servlet.filament;

import repository.FilamentJsonRepository;
import repository.FilamentRepositoryInterface;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class FilamentDeleteServlet extends HttpServlet {

    private FilamentRepositoryInterface filamentRepository;

    @Override
    public void init() {
        String dbPath = getServletContext().getRealPath("/WEB-INF/filament_db.json");
        this.filamentRepository = new FilamentJsonRepository(dbPath);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (id != null && !id.isBlank()) {
            filamentRepository.deleteByIdFilament(id);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}