package controller;

import com.model.department.Department;
import com.model.department.DepartmentAdd;
import com.model.employee.Employee;
import com.model.employee.EmployeeAdd;
import com.model.resume.Resume;
import com.model.resume.ResumeAdd;
import com.model.schedule.Schedule;
import com.model.schedule.ScheduleAdd;
import com.model.user.User;
import com.model.user.UserAdd;
import com.model.user.UserTable;
import connection.ConnectionManager;
import database.handler.DBHandler;

import java.io.IOException;

public class ServerController {
    private ConnectionManager connectionManager;
    private DBHandler dbHandler;

    public ServerController(ConnectionManager connectionManager) {
        this.dbHandler = new DBHandler();
        this.connectionManager = connectionManager;
    }

    public void work() throws IOException {
        String message = null;
        do {
            switch (message = connectionManager.readString()) {
                case "authorize":
                    System.out.println("authorize");
                    connectionManager.sendObject(Operations.GetAuthorization((User)connectionManager.readObject(), this.dbHandler));
                    break;
                case "regist":
                    System.out.println("regist");
                    connectionManager.sendObject(Operations.GetRegistration((User)connectionManager.readObject(), this.dbHandler));
                    break;




                case "addEmployee":
                    System.out.println("addEmployee");
                    Operations.AddEmployee((EmployeeAdd)connectionManager.readObject(), this.dbHandler);
                    break;
                case "addResume":
                    System.out.println("addResume");
                    Operations.AddResume((ResumeAdd)connectionManager.readObject(), this.dbHandler);
                    break;
                case "addDepartment":
                    System.out.println("addDepartment");
                    Operations.AddDepartment((DepartmentAdd)connectionManager.readObject(), this.dbHandler);
                    break;
                case "addSchedule":
                    System.out.println("addSchedule");
                    Operations.AddSchedule((ScheduleAdd)connectionManager.readObject(), this.dbHandler);
                    break;
                case "addUser":
                    System.out.println("addUser");
                    Operations.AddUser((UserAdd)connectionManager.readObject(), this.dbHandler);
                    break;



                case "allEmployeeTable":
                    System.out.println("allEmployeeTable");
                    connectionManager.sendObject(Operations.GetAllEmployee(this.dbHandler));
                    break;
                case "AllResumeTable":
                    System.out.println("AllResumeTable");
                    connectionManager.sendObject(Operations.GetAllResume(this.dbHandler));
                    break;
                case "AllDepartmentTable":
                    System.out.println("AllDepartmentTable");
                    connectionManager.sendObject(Operations.GetAllDepartment(this.dbHandler));
                    break;
                case "AllScheduleTable":
                    System.out.println("AllScheduleTable");
                    connectionManager.sendObject(Operations.GetAllSchedule(this.dbHandler));
                    break;
                case "AllUserTable":
                    System.out.println("AllUserTable");
                    connectionManager.sendObject(Operations.GetAllUser(this.dbHandler));
                    break;



                    //Удаление записей из соответсвующих таблиц
                case "deleteEmployee":
                    System.out.println("deleteEmployee");
                    Operations.DeleteEmployee(connectionManager.readString(), this.dbHandler);
                    break;
                case "deleteResume":
                    System.out.println("deleteResume");
                    Operations.DeleteResume(connectionManager.readString(), this.dbHandler);
                    break;
                case "deleteDepartment":
                    System.out.println("deleteDepartment");
                    Operations.DeleteDepartment(connectionManager.readString(), this.dbHandler);
                    break;
                case "deleteSchedule":
                    System.out.println("deleteSchedule");
                    Operations.DeleteSchedule(connectionManager.readString(), this.dbHandler);
                    break;
                case "deleteUser":
                    System.out.println("deleteUser");
                    Operations.DeleteUser(connectionManager.readString(), this.dbHandler);
                    break;



                    //Изменение записей в соответсвующих таблицах
                case "updateEmployee":
                    System.out.println("updateEmployee");
                    Operations.UpdateEmployee((Employee)connectionManager.readObject(), this.dbHandler);
                    break;
                case "updateResume":
                    System.out.println("updateResume");
                    Operations.UpdateResume((Resume)connectionManager.readObject(), this.dbHandler);
                    break;
                case "updateDepartment":
                    System.out.println("updateDepartment");
                    Operations.UpdateDepartment((Department)connectionManager.readObject(), this.dbHandler);
                    break;
                case "updateSchedule":
                    System.out.println("updateSchedule");
                    Operations.UpdateSchedule((Schedule)connectionManager.readObject(), this.dbHandler);
                    break;
                case "updateUser":
                    System.out.println("updateUser");
                    Operations.UpdateUser((UserTable)connectionManager.readObject(), this.dbHandler);
                    break;
                case "close":
                    System.out.println("close");
                    connectionManager.close();
                    message = null;
                    break;
                default:
                    System.out.println("not " + message);
                    break;
            }
        } while (message != null);
    }
}
