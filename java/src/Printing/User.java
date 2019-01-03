package Printing;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  protected String name = SeqUtil.toStr(SeqUtil.seq());
  protected String password = SeqUtil.toStr(SeqUtil.seq());
  protected String birthDay = SeqUtil.toStr(SeqUtil.seq());
  protected Object role;

  protected void initUser(final String username, final String pass, final Object rol) {

    name = username;
    password = pass;
    role = rol;
  }

  public String getName() {

    return name;
  }

  public Object getRole() {

    return role;
  }

  public Malfunction reportMalfunction(
      final Printer printer, final Object type, final String desc) {

    Malfunction malf = new Malfunction(this, printer, ((Object) type), desc);
    PrintManager.getInstance().addMalfunction(malf);
    return malf;
  }

  public Boolean isLoginCorrected(final String username, final String pass) {

    Boolean andResult_52 = false;

    if (Utils.equals(name, username)) {
      if (Utils.equals(password, pass)) {
        andResult_52 = true;
      }
    }

    if (andResult_52) {
      return true;
    }

    return false;
  }

  public User() {}

  public String toString() {

    return "User{"
        + "name := "
        + Utils.toString(name)
        + ", password := "
        + Utils.toString(password)
        + ", birthDay := "
        + Utils.toString(birthDay)
        + ", role := "
        + Utils.toString(role)
        + "}";
  }
}
