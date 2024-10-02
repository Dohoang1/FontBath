package controller;

import dao.PersonDAO;
import model.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "PersonServlet", urlPatterns = "/persons")
public class PersonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PersonDAO personDAO;

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

    private void listPersons(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Person> listPerson;

        String typeSearch = request.getParameter("searchtype");
        String keyword = request.getParameter("keyword");
        String sortBy = request.getParameter("sortby");
        String order = request.getParameter("order");

        listPerson = personDAO.selectAllPersons();

        if (typeSearch != null && keyword != null && !keyword.isEmpty()) {
            listPerson = searchPersons(typeSearch, keyword);
        }

        if (sortBy != null && order != null) {
            listPerson = sortPersons(sortBy, order);
        }

        request.setAttribute("listPerson", listPerson);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/list.jsp");
        dispatcher.forward(request, response);
    }

    private List<Person> searchPersons(String typeSearch, String keyword) throws SQLException {
        switch (typeSearch) {
            case "Name":
                return personDAO.searchPersonsByName(keyword);
            case "ID":
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
        Person newPerson = new Person(name, gender, age, inscription);
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
        Person updatedPerson = new Person(id, name, gender, age, inscription);
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