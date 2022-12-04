package database.handler;

import com.model.department.Department;
import com.model.department.DepartmentAdd;
import com.model.employee.Employee;
import com.model.employee.EmployeeAdd;
import com.model.resume.Resume;
import com.model.schedule.Schedule;
import com.model.resume.ResumeAdd;
import com.model.schedule.ScheduleAdd;
import com.model.user.CurrentUser;
import com.model.user.User;
import com.model.user.UserAdd;
import com.model.user.UserTable;
import com.hashing.JBCrypt;
import database.literals.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import static database.literals.Сonstants.*;

public class DBHandler extends Configuration implements Handler{
    private Connection dbConnection;

    public DBHandler() {
        getDBConnection();
    }

    @Override
    public Connection getDBConnection() {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            return dbConnection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User Authorization(User user) {
        var listOfUsers = new ArrayList<User>();
        var getAuthorization = "SELECT "
                + USERS_ID + ", " + USERS_LOGIN + ", "
                + USERS_PASSWORD + ", " + USERS_ROLE
                + " FROM " + USERS_TABLE + " WHERE "
                + USERS_LOGIN + " = ?";
        try {
            var request = dbConnection.prepareStatement(getAuthorization);
            request.setString(1, user.getLogin());
            var table = request.executeQuery();
            while (table.next()) {
                CurrentUser candidateOnInter;
                if (!JBCrypt.checkpw(user.getPassword(), table.getString(3))) {
                    System.out.println(table.getString(3));
                    System.out.println(user.getPassword());
                    System.out.println("null");
                    return null;
                }
                user.setRole(table.getString(4));
                System.out.println(user.getRole());
                System.out.println(user.getLogin());
                return user;
            }
            return null;

            /*prSt.setString(2, user.getPassword());
            var resSet = prSt.executeQuery();
            while (resSet.next()) {
                System.out.println("good");
                return user;
            }*/
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //System.out.println("bad");
        //return null;
    }

    public Object Registration(User user) {
        var countOfUsers = 0;
        var listOfUsers = new ArrayList<User>();
        var getUsers = "SELECT " + USERS_LOGIN + " FROM " + USERS_TABLE
                + " WHERE " + USERS_LOGIN + " = ?";
        try {
            var request = dbConnection.prepareStatement(getUsers);
            request.setString(1, user.getLogin());
            var table = request.executeQuery();
            while (table.next()) {
                countOfUsers++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (countOfUsers != 0) {
            return null;
        }
        var getRegistration = "INSERT INTO " + USERS_TABLE + " (" + USERS_LOGIN + ", "
                + USERS_PASSWORD + ", " + USERS_ROLE + ") VALUES (?, ?, ?)";
        try {
            var i = 0;
            var request = dbConnection.prepareStatement(getRegistration);
            request.setString(1, user.getLogin());
            request.setString(2, JBCrypt.hashpw(user.getPassword(), JBCrypt.gensalt()));
            request.setString(3, user.getRole());
            request.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }










    @Override
    public void AddEmployee(EmployeeAdd employeeAdd) {
        var insertEmployee = "INSERT INTO `coursework`.`employeesinfo` (`nameemployeesinfo`, `resumesinfo_idresumesinfo`, `departmentsinfo_iddepartmentsinfo`, `schedulesinfo_idschedulesinfo`) VALUES (?, ?, ?, ?);";
        try {
            var requestInsertEmployee = dbConnection.prepareStatement(insertEmployee);
            requestInsertEmployee.setString(1, employeeAdd.getName());
            requestInsertEmployee.setInt(2, employeeAdd.getResumeId());
            requestInsertEmployee.setInt(3,employeeAdd.getDepartmentId());
            requestInsertEmployee.setInt(4,employeeAdd.getScheduleId());
            requestInsertEmployee.executeUpdate();
        } catch (Exception e) { }
    }
    @Override
    public void AddResume(ResumeAdd resumeAdd) {
        var insertResume = "INSERT INTO `coursework`.`resumesinfo` (`nameresumesinfo`, `skillsresumesinfo`) VALUES (?, ?);";
        try {
            var requestInsertEmployee = dbConnection.prepareStatement(insertResume);
            requestInsertEmployee.setString(1, resumeAdd.getName());
            requestInsertEmployee.setString(2, resumeAdd.getSkills());
            requestInsertEmployee.executeUpdate();
        } catch (Exception e) { }
    }
    @Override
    public void AddDepartment(DepartmentAdd departmentAdd) {
        var insertDepartment = "INSERT INTO `coursework`.`departmentsinfo` (`namedepartmentsinfo`) VALUES (?);";
        try {
            var requestInsertDepartment = dbConnection.prepareStatement(insertDepartment);
            requestInsertDepartment.setString(1, departmentAdd.getName());
            requestInsertDepartment.executeUpdate();
        } catch (Exception e) { }
    }
    @Override
    public void AddSchedule(ScheduleAdd scheduleAdd) {
        var insertSchedule = "INSERT INTO `coursework`.`schedulesinfo` (`scheduleschedulesinfo`) VALUES (?);";
        try {
            var requestInsertSchedule = dbConnection.prepareStatement(insertSchedule);
            requestInsertSchedule.setString(1, scheduleAdd.getSchedule());
            requestInsertSchedule.executeUpdate();
        } catch (Exception e) { }
    }
    @Override
    public void AddUser(UserAdd userAdd) {
        var insertUser = "INSERT INTO `coursework`.`usersinfo` (`loginusersinfo`, `passwordusersinfo`, `roleusersinfo`) VALUES (?, ?, ?);";
        try {
            var requestInsertUser = dbConnection.prepareStatement(insertUser);
            requestInsertUser.setString(1, userAdd.getLogin());
            requestInsertUser.setString(2, userAdd.getPassword());
            requestInsertUser.setString(3, userAdd.getRole());
            requestInsertUser.executeUpdate();
        } catch (Exception e) { }
    }



















    public LinkedList<Employee> AllEmployee(){
        var employeesList = new LinkedList<Employee>();
        var getEmployees = "SELECT idemployeesinfo, nameemployeesinfo, resumesinfo_idresumesinfo, skillsresumesinfo, iddepartmentsinfo, namedepartmentsinfo, schedulesinfo_idschedulesinfo, scheduleschedulesinfo\n" +
                "FROM coursework.employeesinfo\n" +
                "INNER JOIN resumesinfo ON resumesinfo.idresumesinfo = employeesinfo.resumesinfo_idresumesinfo\n" +
                "INNER JOIN departmentsinfo ON departmentsinfo.iddepartmentsinfo = employeesinfo.departmentsinfo_iddepartmentsinfo\n" +
                "INNER JOIN schedulesinfo ON schedulesinfo.idschedulesinfo = employeesinfo.schedulesinfo_idschedulesinfo;";
        try {
            var requestAllEmployee = dbConnection.prepareStatement(getEmployees);
            var tableEmployees = requestAllEmployee.executeQuery();

            while (tableEmployees.next()) {
                var employee = new Employee(tableEmployees.getInt(1), tableEmployees.getString(2), tableEmployees.getInt(3), tableEmployees.getString(4), tableEmployees.getInt(5), tableEmployees.getString(6), tableEmployees.getInt(7), tableEmployees.getString(8));
                employeesList.add(employee);
            }

        }catch (Exception e){ }

        return employeesList;
    }

    public LinkedList<Resume> AllResume(){
        var resumesList = new LinkedList<Resume>();
        var getResumes = "SELECT * FROM coursework.resumesinfo;";
        try {
            var requestAllResume = dbConnection.prepareStatement(getResumes);
            var tableResumes = requestAllResume.executeQuery();

            while (tableResumes.next()) {
                var resume = new Resume(tableResumes.getInt(1), tableResumes.getString(2), tableResumes.getString(3));
                resumesList.add(resume);
            }

        }catch (Exception e){ }

        return resumesList;
    }

    public LinkedList<Department> AllDepartment(){
        var departmentsList = new LinkedList<Department>();
        var getDepartments = "SELECT * FROM coursework.departmentsinfo;";
        try {
            var requestAllDepartment = dbConnection.prepareStatement(getDepartments);
            var tableDepartments = requestAllDepartment.executeQuery();

            while (tableDepartments.next()) {
                var department = new Department(tableDepartments.getInt(1), tableDepartments.getString(2));
                departmentsList.add(department);
            }

        }catch (Exception e){ }

        return departmentsList;
    }
    public LinkedList<Schedule> AllSchedule(){
        var SchedulesList = new LinkedList<Schedule>();
        var getSchedules = "SELECT * FROM coursework.schedulesinfo;";
        try {
            var requestAllSchedule = dbConnection.prepareStatement(getSchedules);
            var tableSchedules = requestAllSchedule.executeQuery();

            while (tableSchedules.next()) {
                var Schedule = new Schedule(tableSchedules.getInt(1), tableSchedules.getString(2));
                SchedulesList.add(Schedule);
            }

        }catch (Exception e){ }

        return SchedulesList;
    }
    public LinkedList<UserTable> AllUser(){
        var UsersList = new LinkedList<UserTable>();
        var getUsers = "SELECT * FROM coursework.usersinfo;";
        try {
            var requestAllUser = dbConnection.prepareStatement(getUsers);
            var tableUsers = requestAllUser.executeQuery();

            while (tableUsers.next()) {
                var User = new UserTable(tableUsers.getInt(1), tableUsers.getString(2), tableUsers.getString(3), tableUsers.getString(4));
                UsersList.add(User);
            }

        }catch (Exception e){ }
        return UsersList;
    }



    //Удаление
    public void DeleteEmployee(String id) {
        var deleteEmployee = "DELETE FROM employeesinfo WHERE idemployeesinfo = ?";
        PreparedStatement request = null;
        try {
            request = dbConnection.prepareStatement(deleteEmployee);
            request.setInt(1, Integer.parseInt(id));
            request.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void DeleteResume(String id) {
        var deleteResume = "DELETE FROM resumesinfo WHERE idresumesinfo = ?";
        PreparedStatement request = null;
        try {
            request = dbConnection.prepareStatement(deleteResume);
            request.setInt(1, Integer.parseInt(id));
            request.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void DeleteDepartment(String id) {
        var deleteDepartment = "DELETE FROM departmentsinfo WHERE iddepartmentsinfo = ?";
        PreparedStatement request = null;
        try {
            request = dbConnection.prepareStatement(deleteDepartment);
            request.setInt(1, Integer.parseInt(id));
            request.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void DeleteSchedule(String id) {
        var deleteSchedule = "DELETE FROM schedulesinfo WHERE idschedulesinfo = ?";
        PreparedStatement request = null;
        try {
            request = dbConnection.prepareStatement(deleteSchedule);
            request.setInt(1, Integer.parseInt(id));
            request.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void DeleteUser(String id) {
        var deleteUser = "DELETE FROM usersinfo WHERE idusersinfo = ?";
        PreparedStatement request = null;
        try {
            request = dbConnection.prepareStatement(deleteUser);
            request.setInt(1, Integer.parseInt(id));
            request.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    //Изменение
    public void UpdateEmployee(Employee employee) {
        var updateEmployee = "UPDATE coursework.employeesinfo SET nameemployeesinfo = ?, resumesinfo_idresumesinfo = ?, departmentsinfo_iddepartmentsinfo = ?, Schedulesinfo_idSchedulesinfo = ? WHERE idemployeesinfo = ?";
        try {
            var request = dbConnection.prepareStatement(updateEmployee);
            request.setString(1, employee.getName());
            request.setInt(2, employee.getResumeId());
            request.setInt(3, employee.getDepartmentId());
            request.setInt(4, employee.getScheduleId());
            request.setInt(5, employee.getEmployeeId());
            request.executeUpdate();
        } catch (Exception e) { }
    }
    public void UpdateResume(Resume resume) {
        var updateResume = "UPDATE coursework.resumesinfo SET nameresumesinfo = ?, skillsresumesinfo = ? WHERE idresumesinfo = ?";
        try {
            var request = dbConnection.prepareStatement(updateResume);
            request.setString(1, resume.getName());
            request.setString(2, resume.getSkills());
            request.setInt(3, resume.getResumeId());
            request.executeUpdate();
        } catch (Exception e) { }
    }
    public void UpdateDepartment(Department department) {
        var updateDepartment = "UPDATE coursework.departmentsinfo SET namedepartmentsinfo = ? WHERE iddepartmentsinfo = ?";
        try {
            var request = dbConnection.prepareStatement(updateDepartment);
            request.setString(1, department.getName());
            request.setInt(2, department.getDepartmentId());
            request.executeUpdate();
        } catch (Exception e) { }
    }
    public void UpdateSchedule(Schedule schedule) {
        var updateSchedules = "UPDATE coursework.schedulesinfo SET scheduleschedulesinfo = ? WHERE idschedulesinfo = ?";
        try {
            var request = dbConnection.prepareStatement(updateSchedules);
            request.setString(1, schedule.getSchedule());
            request.setInt(2, schedule.getScheduleId());
            request.executeUpdate();
        } catch (Exception e) { }
    }
    public void UpdateUser(UserTable userTable) {
        var updateUser = "UPDATE coursework.usersinfo SET loginusersinfo = ?, passwordusersinfo = ?, roleusersinfo = ?  WHERE idusersinfo = ?";
        try {
            var request = dbConnection.prepareStatement(updateUser);
            request.setString(1, userTable.getLogin());
            request.setString(2, userTable.getPassword());
            request.setString(3, userTable.getRole());
            request.setInt(4, userTable.getId());
            request.executeUpdate();
        } catch (Exception e) { }
    }
}
