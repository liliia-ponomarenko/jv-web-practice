package mate.controller;

import mate.lib.Injector;
import mate.model.Driver;
import mate.service.DriverService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/drivers/createDriver")
public class CreateDriverController extends HttpServlet {
    private final Injector injector = Injector.getInstance("mate");
    private final DriverService driverService
            = (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/drivers/createDriver.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Driver driver = new Driver();
        driver.setName(req.getParameter("driver_name"));
        driver.setLicenseNumber(req.getParameter("license_number"));
        driverService.create(driver);
        resp.sendRedirect(req.getContextPath() + "/index");
    }
}