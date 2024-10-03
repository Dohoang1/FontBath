package controller;

import dao.PersonDAO;
import model.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "PersonServlet", urlPatterns = "/persons")
public class PersonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PersonDAO personDAO;
    private static final int RECORDS_PER_PAGE = 5;

    public void init() {
        personDAO = new PersonDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertPerson(request, response);
                    break;
                case "edit":
                    updatePerson(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                case "edit":
                case "delete":
                    if (!isAdminLoggedIn(request)) {
                        response.sendRedirect("login.jsp");
                        return;
                    }
                    break;
            }

            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deletePerson(request, response);
                    break;
                default:
                    listPersons(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private boolean isAdminLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("admin") != null;
    }


    private void listPersons(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        String typeSearch = request.getParameter("searchtype");
        String keyword = request.getParameter("keyword");
        String sortBy = request.getParameter("sortby");
        String order = request.getParameter("order");

        List<Person> listPerson;
        int totalRecords;

        if (typeSearch != null && keyword != null && !keyword.isEmpty()) {
            listPerson = searchPersons(typeSearch, keyword);
        } else if (sortBy != null && order != null) {
            listPerson = sortPersons(sortBy, order);
        } else {
            listPerson = personDAO.selectAllPersons();
        }

        totalRecords = listPerson.size();
        int totalPages = (int) Math.ceil((double) totalRecords / RECORDS_PER_PAGE);

        int start = (page - 1) * RECORDS_PER_PAGE;
        int end = Math.min(start + RECORDS_PER_PAGE, totalRecords);

        List<Person> pagePersons = listPerson.subList(start, end);

        request.setAttribute("listPerson", pagePersons);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("recordsPerPage", RECORDS_PER_PAGE);
        request.setAttribute("totalRecords", totalRecords);
        request.setAttribute("searchtype", typeSearch);
        request.setAttribute("keyword", keyword);
        request.setAttribute("sortby", sortBy);
        request.setAttribute("order", order);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/list.jsp");
        dispatcher.forward(request, response);
    }

    private List<Person> searchPersons(String typeSearch, String keyword) throws SQLException {
        switch (typeSearch) {
            case "name":
                return personDAO.searchPersonsByName(keyword);
            case "id":
                return personDAO.searchPersonsById(keyword);
            default:
                return personDAO.selectAllPersons();
        }
    }

    private List<Person> sortPersons(String sortBy, String order) throws SQLException {
        switch (sortBy) {
            case "ID":
                return personDAO.sortPersonById(order);
            case "Name":
                return personDAO.sortPersonByName(order);
            default:
                return personDAO.selectAllPersons();
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Person existingPerson = personDAO.selectPerson(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/edit.jsp");
        request.setAttribute("person", existingPerson);
        dispatcher.forward(request, response);
    }

    private void insertPerson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        String inscription = request.getParameter("inscription");
        String image = request.getParameter("image");
        Person newPerson = new Person(name, gender, age, inscription, image);
        personDAO.insertPerson(newPerson);
        response.sendRedirect("persons");
    }

    private void updatePerson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        String inscription = request.getParameter("inscription");
        String image = request.getParameter("image");
        Person updatedPerson = new Person(id, name, gender, age, inscription, image);
        personDAO.updatePerson(updatedPerson);
        response.sendRedirect("persons");
    }

    private void deletePerson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        personDAO.deletePerson(id);
        response.sendRedirect("persons");
    }
}