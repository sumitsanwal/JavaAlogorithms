import java.util.ArrayList;
import java.util.TreeMap;

public class TreeMapCeilingFloorExample {
    public static void main(String[] args) {
        TreeMap<Integer, Object> tenureTreeMap = new TreeMap<>();
        /* employee List, for illustration purpose just creating few dummy entries but in actual it can be read from source
         * and can run into very high numbers including all employees for the organization */
        ArrayList<Employee> empList = new ArrayList<>();
        empList.add(new Employee("John", "Engineer", 24, "A1", 6,150000));
        empList.add(new Employee("David", "Manager", 35, "A2", 3,145000));
        empList.add(new Employee("Fred", "Architect", 41, "B1", 2, 155000));
        empList.add(new Employee("Brad", "Engineer", 21, "A3", 8,120000));
        empList.add(new Employee("Jason", "Engineer", 32, "A1", 4,170000));
        empList.add(new Employee("Adam", "Senior Engineer", 12, "A1", 14,85000));
        empList.add(new Employee("Christie", "Director", 10, "A1", 15,178000));
        empList.add(new Employee("Martha", "Accountant", 26, "A1", 6,85000));
        empList.add(new Employee("Diana", "Engineer", 28, "A1", 6,108000));
        empList.add(new Employee("Lisa", "Developer", 14, "A1", 12,82000));
         /*  Adding the default buckets for the employee tenures with 0-5, 6- 10, 11-15, 16-20, 21-25, 26-30
         * Adding only higher bound as key so can efficiently use ceiling function to categorize all employees into right bucket */
        tenureTreeMap.put(5, new ArrayList<Employee>());
        tenureTreeMap.put(10, new ArrayList<Employee>());
        tenureTreeMap.put(15, new ArrayList<Employee>());
        tenureTreeMap.put(20, new ArrayList<Employee>());
        tenureTreeMap.put(25, new ArrayList<Employee>());
        tenureTreeMap.put(30, new ArrayList<Employee>());
        /* Using ceilingKey function to check for every employee record and adding the employee to the right bucket.
         * This is a simple illustration leveraging ceilingKey function to easily bucket employees into the right bucket */
        for (Employee employee : empList) {
            Integer key = tenureTreeMap.ceilingKey(employee.getTenure());
            ArrayList matchingList = (ArrayList) tenureTreeMap.get(key);
            matchingList.add(employee);
            tenureTreeMap.put(key, matchingList);
        }
        // Printing all Employees in the bucket starting with lowest to highest
        tenureTreeMap.forEach((key, value) -> {
            System.out.println("Key : " + key + " Value : ");
            ((ArrayList) value).forEach(empObj -> System.out.println(empObj.toString()));
        });

        //Identifying tenure bucket for every employee including lower bound and upper bound
        empList.forEach(employeeObj -> {
            int upperBound = tenureTreeMap.ceilingEntry(employeeObj.getTenure()).getKey();
            Integer lowerBound = tenureTreeMap.lowerKey(employeeObj.getTenure()); if(lowerBound==null) lowerBound = 1;
            String tenureRange = lowerBound+" - "+ upperBound;
            System.out.println("Emloyee : "+ employeeObj + " belongs to the tenure range -> " + tenureRange);
        });
    }

}

class Employee {
    String empName;
    String designation;
    Integer empId;
    String lastYearRating;
    Integer tenure;
    Integer currentSalary;

    public Employee(String empName, String designation, Integer empId, String lastYearRating, Integer tenure, Integer currentSalary) {
        this.empName = empName;
        this.designation = designation;
        this.empId = empId;
        this.lastYearRating = lastYearRating;
        this.tenure = tenure;
        this.currentSalary = currentSalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empName='" + empName + '\'' +
                ", designation='" + designation + '\'' +
                ", empId=" + empId +
                ", lastYearRating='" + lastYearRating + '\'' +
                ", tenure=" + tenure +
                ", currentSalary=" + currentSalary +
                '}';
    }

    public Integer getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(Integer currentSalary) {
        this.currentSalary = currentSalary;
    }

   public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getLastYearRating() {
        return lastYearRating;
    }

    public void setLastYearRating(String lastYearRating) {
        this.lastYearRating = lastYearRating;
    }

    public Integer getTenure() {
        return tenure;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }

}

