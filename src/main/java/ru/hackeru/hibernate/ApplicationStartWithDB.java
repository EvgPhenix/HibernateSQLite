package ru.hackeru.hibernate;

import ru.hackeru.hibernate.DAO.AddressDAOImpl;
import ru.hackeru.hibernate.DAO.DAO;
import ru.hackeru.hibernate.DAO.DepartmentDAOImpl;
import ru.hackeru.hibernate.DAO.EmplyeeDAOImpl;
import ru.hackeru.hibernate.Entity.*;

import java.util.ArrayList;
import java.util.List;

public class ApplicationStartWithDB {
    public static void main(String[] args) {

        // Здесь реализовано наполнение, вывод, получение и удаление элементов

        List<Address> addressList = new ArrayList<>();
        addressList.add(new Address("Moscow", "Tverskaya", 11, 44));
        addressList.add(new Address("Ryazan", "Lenona", 34, 45));
        addressList.add(new Address("Tula", "Pervomaya", 120, 63));
        addressList.add(new Address("Muhosransk", "Tugosplavaskaya", 32, 100));
        addressList.add(new Address("Kirovochepetsk", "Tupoleva", 43, 47));
        addressList.add(new Address("Spb", "Nevskiy", 14, 100));
        addressList.add(new Address("Eburg", "Prospekt 100500 let Oktabrya", 3500, 100500));
        addressList.add(new Address("Ebenevo", "Abakumova", 1, 23));
        addressList.add(new Address("Kukuevka", "Artema", 2, 2));
        addressList.add(new Address("Uryupinsk", "Dzerjinskogo", 13, 90));

        List<Department> departmentList = new ArrayList<>();
        departmentList.add(new Department("IT"));
        departmentList.add(new Department("Finance"));
        departmentList.add(new Department("Marketing"));
        departmentList.add(new Department( "Sales"));
        departmentList.add(new Department( "Support"));

        DAO<Department> departmentDAO = new DepartmentDAOImpl();

        // Наполнение таблицы Department(если нужно)
        for (Department department : departmentList) {
                departmentDAO.add(department);
            }

        DAO<Address> addressDAO = new AddressDAOImpl();
        // Наполнение таблицы Address(если нужно)
        for (Address address : addressList) {
                addressDAO.add(address);
        }

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Василий", 35000, 23, addressDAO.getById(1), departmentDAO.getById(2)));
        employeeList.add(new Employee("Виктор", 45000, 24, addressDAO.getById(2), departmentDAO.getById(2)));
        employeeList.add(new Employee("Михаил", 125000, 38, addressDAO.getById(1), departmentDAO.getById(3)));
        employeeList.add(new Employee("Вадим", 55000, 29, addressDAO.getById(1), departmentDAO.getById(1)));
        employeeList.add(new Employee("Дмитрий", 75000, 31, addressDAO.getById(1), departmentDAO.getById(1)));
        employeeList.add(new Employee("Евгений", 85000, 33, addressDAO.getById(1), departmentDAO.getById(1)));
        employeeList.add(new Employee( "Илья", 45000, 22, addressDAO.getById(1), departmentDAO.getById(1)));
        employeeList.add(new Employee( "Максим", 65000, 26, addressDAO.getById(2), departmentDAO.getById(2)));
        employeeList.add(new Employee("Алексей", 25000, 20, addressDAO.getById(2), departmentDAO.getById(2)));
        employeeList.add(new Employee("Владимир", 55000, 28, addressDAO.getById(2), departmentDAO.getById(2)));
        employeeList.add(new Employee("Игорь", 95000, 36, addressDAO.getById(3), departmentDAO.getById(3)));
        employeeList.add(new Employee("Андрей", 45000, 23, addressDAO.getById(3), departmentDAO.getById(3)));
        employeeList.add(new Employee("Егор", 35000, 22, addressDAO.getById(3), departmentDAO.getById(3)));
        employeeList.add(new Employee("Станислав", 75000, 29, addressDAO.getById(4), departmentDAO.getById(4)));
        employeeList.add(new Employee("Никита", 49000, 24, addressDAO.getById(4), departmentDAO.getById(4)));
        employeeList.add(new Employee("Александр", 60000, 28, addressDAO.getById(5), departmentDAO.getById(4)));
        employeeList.add(new Employee("Денис", 50000, 25, addressDAO.getById(5), departmentDAO.getById(4)));
        employeeList.add(new Employee("Юлия", 70000, 27, addressDAO.getById(5), departmentDAO.getById(5)));
        employeeList.add(new Employee("Анна", 40000, 22, addressDAO.getById(5), departmentDAO.getById(5)));
        employeeList.add(new Employee("Яна", 55000, 26, addressDAO.getById(5), departmentDAO.getById(5)));
        employeeList.add(new Employee("Владислав", 78000, 24, addressDAO.getById(6), departmentDAO.getById(3)));
        employeeList.add(new Employee("Екатерина", 29000, 21, addressDAO.getById(7), departmentDAO.getById(2)));
        employeeList.add(new Employee("Виктория", 68000, 29, addressDAO.getById(8), departmentDAO.getById(5)));
        employeeList.add(new Employee("Георгий", 57000, 23, addressDAO.getById(9), departmentDAO.getById(1)));
        employeeList.add(new Employee("Елена", 38000, 26, addressDAO.getById(8), departmentDAO.getById(5)));


        DAO<Employee> employeeDAO = new EmplyeeDAOImpl();
        // Наполнение таблицы Employee(если нужно)
        for (Employee employee : employeeList) {
                employeeDAO.add(employee);
        }

// deleting with superclass DAOImplAbstract is working!!!!

//        for (int i = 3; i < 75; i++) {
//            employeeDAO.deleteById(i);
//        }


//this deleting with superClass works too


//        departmentDAO.deleteById(6);
//        addressDAO.deleteById(11);


//getAll() from DAOImplAbstract works too

//        List<Address> addresses = addressDAO.getAll();
//        for (Address address : addresses) {
//            System.out.println(address.toString());
//        }


// Вывод

        for (int i = 1; i < 5; i++) {
            System.out.println(departmentDAO.getById(i).toString());
        }
        for (int i = 75; i < 101; i++) {
            System.out.println(employeeDAO.getById(i));

        }
        for (int i = 1; i < 10; i++) {
            System.out.println(addressDAO.getById(i).toString());
        }
    }
}
