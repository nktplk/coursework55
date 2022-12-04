package controller;

import com.model.department.Department;
import com.model.department.DepartmentAdd;
import com.model.employee.Employee;
import com.model.employee.EmployeeAdd;
import com.model.resume.Resume;
import com.model.schedule.Schedule;
import com.model.resume.ResumeAdd;
import com.model.schedule.ScheduleAdd;
import com.model.user.User;
import com.model.user.UserAdd;
import com.model.user.UserTable;
import database.handler.Handler;

import java.util.LinkedList;

public class Operations {
    public static User GetAuthorization(User user, Handler handler) { return handler.Authorization(user); }
    public static Object GetRegistration(User user, Handler handler) { return handler.Registration(user); }



    public static void AddEmployee(EmployeeAdd employeeAdd, Handler handler) { handler.AddEmployee(employeeAdd); }
    public static void AddResume(ResumeAdd resumeAdd, Handler handler) { handler.AddResume(resumeAdd); }
    public static void AddDepartment(DepartmentAdd departmentAdd, Handler handler) { handler.AddDepartment(departmentAdd); }
    public static void AddSchedule(ScheduleAdd scheduleAdd, Handler handler) { handler.AddSchedule(scheduleAdd); }
    public static void AddUser(UserAdd userAdd, Handler handler) { handler.AddUser(userAdd); }



    public static LinkedList<Employee> GetAllEmployee(Handler handler) { return handler.AllEmployee(); }
    public static LinkedList<Resume> GetAllResume(Handler handler) { return handler.AllResume(); }
    public static LinkedList<Department> GetAllDepartment(Handler handler) { return handler.AllDepartment(); }
    public static LinkedList<Schedule> GetAllSchedule(Handler handler) { return handler.AllSchedule(); }
    public static LinkedList<UserTable> GetAllUser(Handler handler) { return handler.AllUser(); }



    //Удаление
    public static void DeleteEmployee(String id, Handler handler) { handler.DeleteEmployee(id); }
    public static void DeleteResume(String id, Handler handler) { handler.DeleteResume(id); }
    public static void DeleteDepartment(String id, Handler handler) { handler.DeleteDepartment(id); }
    public static void DeleteSchedule(String id, Handler handler) { handler.DeleteSchedule(id); }
    public static void DeleteUser(String id, Handler handler) { handler.DeleteUser(id); }



    public static void UpdateEmployee(Employee employee, Handler handler) { handler.UpdateEmployee(employee); }
    public static void UpdateResume(Resume resume, Handler handler) { handler.UpdateResume(resume); }
    public static void UpdateDepartment(Department department, Handler handler) { handler.UpdateDepartment(department); }
    public static void UpdateSchedule(Schedule schedule, Handler handler) { handler.UpdateSchedule(schedule); }
    public static void UpdateUser(UserTable userTable, Handler handler) { handler.UpdateUser(userTable); }
}
