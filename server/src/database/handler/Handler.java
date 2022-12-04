package database.handler;

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
import java.sql.Connection;
import java.util.LinkedList;

public interface Handler {
    Connection getDBConnection();



    User Authorization(User user);
    Object Registration(User user);



    //Выборка всех записей в таблицах
    LinkedList<Employee> AllEmployee();
    LinkedList<Resume> AllResume();
    LinkedList<Department> AllDepartment();
    LinkedList<Schedule> AllSchedule();
    LinkedList<UserTable> AllUser();



    //Добавление записей в соответсвующую таблицу
    void AddEmployee(EmployeeAdd employeeAdd);
    void AddResume(ResumeAdd resumeAdd);
    void AddDepartment(DepartmentAdd department);
    void AddSchedule(ScheduleAdd Schedule);
    void AddUser(UserAdd userAdd);



    //Изменение записей в соответсвующих таблицах
    void UpdateEmployee(Employee employee);
    void UpdateResume(Resume resume);
    void UpdateDepartment(Department department);
    void UpdateSchedule(Schedule schedule);
    void UpdateUser(UserTable userTable);



    //Удаление записей в соответсвующих таблицах
    void DeleteEmployee(String id);
    void DeleteResume(String id);
    void DeleteDepartment(String id);
    void DeleteSchedule(String id);
    void DeleteUser(String id);
}