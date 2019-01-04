package Printing;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Employee extends User {
  private VDMSet malfunctions = SetUtil.set();
  private Object role;

  public void cg_init_Employee_1(
      final String username, final String password_1, final Object newRole) {

    initUser(username, password_1);
    role = newRole;
  }

  public Employee(final String username, final String password_1, final Object newRole) {

    cg_init_Employee_1(username, password_1, newRole);
  }

  public Object getRole() {

    return role;
  }

  public void addMalfunction(final Malfunction malf) {

    malfunctions = SetUtil.union(Utils.copy(malfunctions), SetUtil.set(malf));
  }

  public VDMSet getMalfunctions() {

    return Utils.copy(malfunctions);
  }

  public void assignMalfunction(final Malfunction malf, final Employee emp) {

    malf.assignEmployee(emp);
    emp.addMalfunction(malf);
  }

  public Employee() {}

  public String toString() {

    return "Employee{"
        + "malfunctions := "
        + Utils.toString(malfunctions)
        + ", role := "
        + Utils.toString(role)
        + "}";
  }
}
