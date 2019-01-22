package ru.hackeru.hibernate;

import ru.hackeru.hibernate.DAO.AddressDAOImpl;
import ru.hackeru.hibernate.DAO.DepartmentDAOImpl;
import ru.hackeru.hibernate.DAO.EmplyeeDAOImpl;
import ru.hackeru.hibernate.Entity.Address;
import ru.hackeru.hibernate.Entity.Department;
import ru.hackeru.hibernate.Entity.Employee;

import java.sql.*;
import java.util.*;


// Решение домашнеого задания

public class ApplicationObrabotka {
    public static void main(String[] args) throws SQLException {

        avgSalary();

        sameCity();

        mostYoungDep();
        
    }

//    // ДЗ №1 Размер ЗП по департаментам

    public static void avgSalary() throws SQLException {

        // Создали list в котором у нас название департамента и его номер для связки
        List<Department> depList = getAllDep();

        List<Employee> listEmployees = getAllEmp();


        //Создали конечную мапу в которую будем грузить
        Map<String, Integer> mapOfAvgSalary = new HashMap<>();
        // наполнили результирующию map данными о департаментах и salary пока равен 0
        for (Department department : depList) {
            mapOfAvgSalary.put(department.getDep_name(), 0);
        }

        for (Department department : depList) {
            System.out.println(department);
        }
        System.out.println();

        // Наполнили конечную мапу значениями

        for (int i = 0; i < listEmployees.size(); i++) {

            int salary = listEmployees.get(i).getSalary();


            if (listEmployees.get(i).getDepartment()==null) continue;
            mapOfAvgSalary.put(listEmployees.get(i).getDepartment().getDep_name(),
                    mapOfAvgSalary.get(listEmployees.get(i).getDepartment().getDep_name())+salary);

        }


        int sum = 0;
        for (Map.Entry<String, Integer> entry : mapOfAvgSalary.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            sum += entry.getValue();
        }
        System.out.println();
        System.out.println("Итого " + sum);
        System.out.println();
    }

    // ДЗ №2 Кто живет в одном городе?

    public static void sameCity() throws SQLException{

        List<Address> listOfAddress = getAllAddr();
        List<Employee> listEmployees = getAllEmp();
        for (Employee employee : listEmployees) {
            System.out.println(employee.toString());
        }
        System.out.println();
        // это для вывода коллекция
        List<String> stringList = new ArrayList<>();

        for (int k = 0; k < listOfAddress.size(); k++) {

            String city = listOfAddress.get(k).getCity();
            //Заполняем stringList имена по каждому городу, затем выводим и очищаем
            for (int i = 0; i < listEmployees.size(); i++) {
                if (listEmployees.get(i).getAddress()!=null && listEmployees.get(i).getAddress().getCity().equals(city))
                    stringList.add(listEmployees.get(i).getName());
            }
            if (!stringList.isEmpty()){
                for (String s : stringList) {
                    System.out.print(s + " ");
                }
                System.out.print(  "живут в городе " + city);
                System.out.println();
            }

            stringList.clear();
        }
        System.out.println();
    }

    // ДЗ №3 Где самые молодые сотрудники?

    public static void mostYoungDep() throws SQLException{


        List<Department> listOfDep = getAllDep();

        List<Employee> listEmployees = getAllEmp();


        Map<String, Integer> resultMap = new HashMap<>();
        List<Integer> listEmpInOneDep = new ArrayList<>();

        for (Department department : listOfDep) {
            for (Employee employee : listEmployees) {
                if (employee.getDepartment()!=null && employee.getDepartment().getEmployee_id()==department.getEmployee_id())
                    listEmpInOneDep.add(employee.getAge());
            }
            resultMap.put(department.getDep_name(), getAverageAgeForDep(listEmpInOneDep));
            listEmpInOneDep.clear();
        }

        // Ищем департамент
        String minAgeDep = "";
        Integer minAge = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            if (minAge>entry.getValue()) {
                minAge=entry.getValue();
                minAgeDep = entry.getKey();
            }
        }
        System.out.println();
        System.out.println(minAgeDep + " " + minAge);



    }

    public static Integer getAverageAgeForDep(List<Integer> list){
        Integer avgAge = list.stream().reduce(0, (a, b) -> a + b);
        return avgAge/list.size();
    }
    public static List<Department> getAllDep(){
        return new DepartmentDAOImpl().getAll();
    }
    public static List<Employee> getAllEmp(){
        return new EmplyeeDAOImpl().getAll();
    }
    public static List<Address> getAllAddr(){
        return new AddressDAOImpl().getAll();
    }
}
