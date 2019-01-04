package Printing;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  protected String name = SeqUtil.toStr(SeqUtil.seq());
  protected String password = SeqUtil.toStr(SeqUtil.seq());
  protected String birthDay = SeqUtil.toStr(SeqUtil.seq());

  protected void initUser(final String username, final String pass) {

    name = username;
    password = pass;
  }

  public String getName() {

    return name;
  }

  public Malfunction reportMalfunction(
      final Printer printer, final Object type, final String desc) {

    Malfunction malf = new Malfunction(this, printer, ((Object) type), desc);
    PrintManager.getInstance().addMalfunction(malf);
    return malf;
  }

  public Boolean isLoginCorrected(final String username, final String pass) {

    Boolean andResult_72 = false;

    if (Utils.equals(name, username)) {
      if (Utils.equals(password, pass)) {
        andResult_72 = true;
      }
    }

    if (andResult_72) {
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
        + "}";
  }
}
